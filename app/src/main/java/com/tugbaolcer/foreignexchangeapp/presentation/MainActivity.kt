package com.tugbaolcer.foreignexchangeapp.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import com.tugbaolcer.foreignexchangeapp.presentation.onboarding.OnboardingScreen
import com.tugbaolcer.foreignexchangeapp.presentation.onboarding.pages
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ForeignExchangeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForeignExchangeAppTheme {
                val pagerState = rememberPagerState(
                    pageCount = { 3 },
                    initialPage = 0,
                    initialPageOffsetFraction = 0f
                )
                OnboardingScreen(item = pages, pagerState = pagerState)
            }
        }
    }
}
