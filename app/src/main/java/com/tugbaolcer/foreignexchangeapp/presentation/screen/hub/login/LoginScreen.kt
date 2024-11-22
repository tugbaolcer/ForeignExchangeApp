package com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.login.LoginViewState
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomAlertMessage
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomButton
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTextField


@Composable
fun LoginScreen(
    navController: NavController,
    onLoginComplete: (() -> Unit)? = null
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .padding(top = 15.dp)
        ) {
            LoginInputFields(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                navController = navController,
                uiState = uiState,
                loginViewModel = viewModel,
                userEmail = email,
                onUserEmailChange = { email = it },
                userPassword = password,
                onPasswordChange = { password = it },
                onLoginComplete = {
                    onLoginComplete?.invoke()
                }
            )
        }

        // Show loading indicator while processing login
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun LoginInputFields(
    modifier: Modifier,
    navController: NavController,
    uiState: LoginViewState,
    loginViewModel: LoginViewModel,
    userEmail: String,
    onUserEmailChange: (String) -> Unit,
    userPassword: String,
    onPasswordChange: (String) -> Unit,
    onLoginComplete: (() -> Unit)? = null
) {
    var isAlertVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Email ve şifre input alanları
        CustomTextField(value = userEmail, onValueChange = onUserEmailChange, label = "Email")
        CustomTextField(
            value = userPassword,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = "Password"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Login butonu
        CustomButton(buttonText = "Login") {
            loginViewModel.onTriggerEvent(LoginEvent.LoginButtonClicked(userEmail, userPassword))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Signup (Kayıt Ol) butonu
        TextButton(onClick = {
            onLoginComplete?.invoke()
        }, modifier = modifier) {
            Text(text = "Don't have an account, Signup")
        }
    }

    // Eğer bir hata mesajı varsa ve login işlemi başarısız olmuşsa
    if (uiState.errorMessage != null) {
        CustomAlertMessage (
            isDisplayed = isAlertVisible,
            title = uiState.errorMessage ?: "Hatasız Kul Olmaz",
            icon = painterResource(R.drawable.ic_warning) ,
            onDismiss = {
                // Hata mesajını temizle
                isAlertVisible = false
                loginViewModel.clearErrorMessage()
            }
        )
    }

    // Başarılı login işleminde yönlendirme
    if (uiState.isLoggedIn) {
        navController.navigate("cryptoScreen")
    } else {
        // Login işlemi sırasında hata oluştuysa alert göster
        if (uiState.errorMessage != null) {
            isAlertVisible = true
        }
    }
}

