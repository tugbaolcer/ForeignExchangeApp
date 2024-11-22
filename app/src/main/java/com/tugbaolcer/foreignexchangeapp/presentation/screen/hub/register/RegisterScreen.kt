package com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.register.RegisterViewState
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomAlertMessage
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomButton
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTextField
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.RoundedCornerCard

@Composable
fun RegisterScreen(onRegisterComplete: (() -> Unit)? = null) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var userName by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerCard.large)
                .padding(top = 15.dp)
        ) {
            RegisterInputFields(
                registerViewModel = viewModel,
                registerState = uiState,
                userName = userName,
                onUserNameChange = { userName = it },
                userSurname = surname,
                onUserSurnameChange = { surname = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                onRegisterClicked = {
                    onRegisterComplete?.invoke()
                }
            )
        }
        // Yükleme durumunu göster
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun RegisterInputFields(
    registerViewModel: RegisterViewModel,
    registerState: RegisterViewState,
    userName: String,
    onUserNameChange: (String) -> Unit,
    userSurname: String,
    onUserSurnameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClicked: () -> Unit
) {

    var isAlertVisible by remember { mutableStateOf(false) }

    Column {
        CustomTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = "Name"
        )
        CustomTextField(
            value = userSurname,
            onValueChange = onUserSurnameChange,
            label = "Surname"
        )
        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email"
        )
        CustomTextField(
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = "Password"
        )
        CustomTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = "Confirm Password"
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(buttonText = "Register") {
            if (password == confirmPassword) {
                registerViewModel.onTriggerEvent(
                    RegisterEvent.RegisterButtonClicked(
                        name = userName,
                        surname = userSurname,
                        email = email,
                        password = password
                    )
                )
                isAlertVisible = true
            }
        }
    }

    // Hata mesajlarını göster
    if (registerState.errorMessage != null) {
        CustomAlertMessage(
            isDisplayed = isAlertVisible,
            title = "Error",
            icon = painterResource(R.drawable.ic_warning), // İlgili ikonunuzu kullanın
            onDismiss = {
                registerViewModel.onTriggerEvent(RegisterEvent.ClearFields)
                isAlertVisible = false
            }
        )
    }

    // Kayıt başarıyla tamamlandıysa
    if (registerState.isSuccess) {
        CustomAlertMessage(
            isDisplayed = isAlertVisible,
            title = "Kayıt Başarılı",
            icon = painterResource(R.drawable.ic_success),
            onDismiss = {
                onRegisterClicked.invoke()
                isAlertVisible = false
            }
        )
    }
}