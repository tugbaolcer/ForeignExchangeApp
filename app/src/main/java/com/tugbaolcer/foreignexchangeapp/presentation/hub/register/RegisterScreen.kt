package com.tugbaolcer.foreignexchangeapp.presentation.hub.register

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.data.dto.UserDto
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
                state = uiState,
                viewModel = viewModel,
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
                onRegisterComplete = { onRegisterComplete?.invoke() }
            )
        }
    }
}

@Composable
fun RegisterInputFields(
    state: RegisterUiState,
    viewModel: RegisterViewModel,
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
    onRegisterComplete: (() -> Unit)? = null
) {
    var isAlertMessageVisible by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    var alertIcon by remember { mutableStateOf(R.drawable.ic_warning) }

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
        label = "Confirmed Password"
    )
    Spacer(modifier = Modifier.height(8.dp))


    when (state) {
        is RegisterUiState.Idle -> {
            CustomButton(buttonText = " Register") {
                if (password == confirmPassword) {
                    viewModel.registerUser(
                        UserDto(
                            name = userName,
                            surname = userSurname,
                            email = email,
                            password = password
                        )
                    )
                }
            }
        }

        is RegisterUiState.Loading -> {
            CircularProgressIndicator()
        }

        is RegisterUiState.Success -> {
            isAlertMessageVisible = true
            alertMessage = "Kayıt Başarılı"
            alertIcon = R.drawable.ic_warning
        }

        is RegisterUiState.Error -> {
            isAlertMessageVisible = true
            alertMessage = state.message
            alertIcon = R.drawable.ic_warning
        }
    }

    CustomAlertMessage(
        isDisplayed = isAlertMessageVisible,
        title = alertMessage,
        icon = painterResource(alertIcon),
        onDismiss = {
            isAlertMessageVisible = false
            onRegisterComplete?.invoke()
        }
    )
}
