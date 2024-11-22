package com.tugbaolcer.foreignexchangeapp.presentation.screen.hub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.login.LoginScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.register.RegisterScreen
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BlackTwoColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.HubBackgroundColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.RoundedCornerCard

@Composable
fun HubScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

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
        ContentBox(navController = navController, selectedTab = selectedTab, onTabSelected = { selectedTab = it })
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
fun ContentBox(navController: NavController, selectedTab: Int, onTabSelected: (Int) -> Unit) {
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
            TabRow(selectedTabIndex = selectedTab, indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(BlackTwoColor)
                )
            }) {
                Tab(
                    text = {
                        Text(
                            "Login",
                            color = if (selectedTab == 0) BlackTwoColor else Color.White
                        )
                    },
                    selected = selectedTab == 0,
                    onClick = { onTabSelected(0) },
                    modifier = Modifier.background(if (selectedTab == 0) Color.White else BlackTwoColor)
                )
                Tab(
                    text = {
                        Text(
                            text = "Register",
                            color = if (selectedTab == 1) BlackTwoColor else Color.White
                        )
                    },
                    selected = selectedTab == 1,
                    onClick = { onTabSelected(1) },
                    modifier = Modifier.background(if (selectedTab == 1) Color.White else BlackTwoColor)
                )
            }

            when (selectedTab) {
                0 -> LoginScreen(navController, onLoginComplete = { onTabSelected(1) })
                1 -> RegisterScreen(onRegisterComplete = { onTabSelected(0) })
            }

        }
    }
}