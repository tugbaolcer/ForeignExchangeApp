package com.tugbaolcer.foreignexchangeapp.presentation.hub

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.SelectedIndicatorColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.UnSelectedIndicatorColor

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
    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        focusedIndicatorColor = SelectedIndicatorColor,
        unfocusedIndicatorColor = UnSelectedIndicatorColor
    )
    OutlinedTextField(
        value = userName,
        onValueChange = onUserNameChange,
        label = { Text("Name") },
        placeholder = { Text("input name") },
        colors = textFieldColors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
    )
    OutlinedTextField(
        value = userSurname,
        onValueChange = onUserSurnameChange,
        label = { Text("Surname") },
        placeholder = { Text("input surname") },
        colors = textFieldColors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
    )
    OutlinedTextField(
        value = phoneNumber,
        onValueChange = onPhoneNumberChange,
        label = { Text("Phone Number") },
        placeholder = { Text("input phone number") },
        colors = textFieldColors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
    )
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Password") },
        placeholder = { Text("input password") },
        colors = textFieldColors,
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
    )
    OutlinedTextField(
        value = rePassword,
        onValueChange = onRePasswordChange,
        label = { Text(text = "Re-Password") },
        placeholder = { Text("input repassword") },
        colors = textFieldColors,
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
    )
}