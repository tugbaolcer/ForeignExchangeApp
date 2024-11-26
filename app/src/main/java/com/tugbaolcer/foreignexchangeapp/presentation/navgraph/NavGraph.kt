package com.tugbaolcer.foreignexchangeapp.presentation.navgraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos.CryptoScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.HubScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.login.LoginScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.onboarding.OnBoardingPager
import com.tugbaolcer.foreignexchangeapp.presentation.screen.onboarding.pages
import com.tugbaolcer.foreignexchangeapp.presentation.screen.stock.StockScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavGraph(
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            val pagerState = rememberPagerState(
                pageCount = { 3 },
                initialPage = 0,
                initialPageOffsetFraction = 0f
            )
            OnBoardingPager(
                item = pages,
                pagerState = pagerState,
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }

        composable("hubScreen") { HubScreen( navController) }
        composable("loginScreen") { LoginScreen(navController) }
        composable("stockScreen") { StockScreen() }

        composable("cryptoScreen") {
            Scaffold { innerPadding ->
                CryptoScreen(innerPadding)
            }
        }
    }
}