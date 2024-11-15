package com.tugbaolcer.foreignexchangeapp.presentation.hub

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTextField

@Composable
fun RegisterInputFields(
    userName: String,
    onUserNameChange: (String) -> Unit,
    userSurname: String,
    onUserSurnameChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    rePassword: String,
    onRePasswordChange: (String) -> Unit
) {

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
        value = phoneNumber,
        onValueChange = onPhoneNumberChange,
        label = "Phone Number"
    )
    CustomTextField(
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation(),
        label = "Password"
    )
    CustomTextField(
        value = rePassword,
        onValueChange = onRePasswordChange,
        visualTransformation = PasswordVisualTransformation(),
        label = "RePassword"
    )
}