package com.tugbaolcer.foreignexchangeapp.presentation.hub.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomButton
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTextField
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.RoundedCornerCard


@Composable
fun LoginScreen(navController: NavController, onLoginComplete: (() -> Unit)? = null) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(modifier = Modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerCard.large)

                .padding(top = 15.dp)
        ) {
            LoginInputFields(
                navController = navController,
                state = uiState,
                viewModel = viewModel,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                userEmail = email,
                onUserEmailChange = { email = it },
                userPassword = password,
                onPasswordChange = { password = it },
                onLoginComplete = { onLoginComplete?.invoke() }
            )
        }
    }
}


@Composable
fun LoginInputFields(
    navController: NavController,
    state: LoginUiState,
    viewModel: LoginViewModel,
    modifier: Modifier,
    userEmail: String,
    onUserEmailChange: (String) -> Unit,
    userPassword: String,
    onPasswordChange: (String) -> Unit,
    onLoginComplete: (() -> Unit)? = null
) {
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    CustomTextField(
        value = userEmail,
        onValueChange = onUserEmailChange,
        label = "Email"
    )

    CustomTextField(
        value = userPassword,
        onValueChange = onPasswordChange,
        label = "Password"
    )

    Spacer(modifier = Modifier.height(8.dp))

    when (state) {
        is LoginUiState.Idle -> {
            CustomButton(buttonText = "Login") {
                viewModel.login(userEmail, userPassword)
            }
        }
        is LoginUiState.Loading -> {
            CircularProgressIndicator()
        }
        is LoginUiState.Success -> {
            navController.navigate("cryptoScreen")
        }
        is LoginUiState.Error -> {
            val error = state.message
            Text(text = error, color = Color.Red)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    TextButton(onClick = {
        onLoginComplete?.invoke()
    }, modifier = modifier) {
        Text(text = "Don't have an account, Signup")
    }
}
