package com.tugbaolcer.foreignexchangeapp.presentation.screen.splash

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.util.Constants.APP_DEVICE_PREFS
import com.tugbaolcer.foreignexchangeapp.util.Constants.hubNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.onboardingNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.stocksNavigationRoute
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferences =
        remember { context.getSharedPreferences(APP_DEVICE_PREFS, Context.MODE_PRIVATE) }

    val isFirstTime = remember { mutableStateOf(sharedPreferences.getBoolean("FIRST_TIME", true)) }
    val isLoggedIn =
        remember { mutableStateOf(sharedPreferences.getBoolean("IS_LOGGED_IN", false)) }

    LaunchedEffect(Unit) {
        delay(3000)

        if (isFirstTime.value) {
            navController.navigate(onboardingNavigationRoute)
            sharedPreferences.edit().putBoolean("FIRST_TIME", false).apply()
            isFirstTime.value = false
        } else {
            if (isLoggedIn.value) {
                navController.navigate(stocksNavigationRoute)
            } else {
                navController.navigate(hubNavigationRoute)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.bg_splash),
                contentDescription = "Foreign Exchange Splash",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Foreign Exchange",
                fontSize = 24.sp,
                color = Color.Black
            )
        }
    }
}


@Composable
@Preview
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}