package com.tugbaolcer.foreignexchangeapp.presentation.hub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomButton
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BlackTwoColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ContainerColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.HubBackgroundColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.RoundedCornerCard

@Composable
fun ContainerPage() {
    var userName by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = HubBackgroundColor)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        HeaderImage()
        ContentBox(
            username = userName,
            onUsernameChange = { userName = it },
            surname = surname,
            onSurnameChange = { surname = it },
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it },
            password = password,
            onPasswordChange = { password = it },
            rePassword = rePassword,
            onRePasswordChange = { rePassword = it },
            onLoginClick = {
            }
        )
    }
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(R.drawable.ic_hub),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ContentBox(
    username: String,
    onUsernameChange: (String) -> Unit,
    surname: String,
    onSurnameChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    rePassword: String,
    onRePasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 300.dp)
            .background(color = HubBackgroundColor)
            .clip(RoundedCornerCard.large)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackTwoColor)
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 15.dp)
            )

            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerCard.large)
                        .background(ContainerColor)
                        .padding(top = 15.dp, )
                ) {
                    RegisterInputFields (
                        userName = username,
                        onUserNameChange = onUsernameChange,
                        userSurname = surname,
                        onUserSurnameChange = onSurnameChange,
                        phoneNumber = phoneNumber,
                        onPhoneNumberChange = onPhoneNumberChange,
                        password = password,
                        onPasswordChange = onPasswordChange,
                        rePassword = rePassword,
                        onRePasswordChange = onRePasswordChange
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomButton(buttonText = "Register") {}
                }
            }

        }
    }
}

