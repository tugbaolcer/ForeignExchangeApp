package com.tugbaolcer.foreignexchangeapp.presentation.navgraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomBottomAppBar
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomScaffold
import com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos.CryptoScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.HubScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.login.LoginScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.onboarding.OnBoardingPager
import com.tugbaolcer.foreignexchangeapp.presentation.screen.onboarding.pages
import com.tugbaolcer.foreignexchangeapp.presentation.screen.stocks.StockScreen
import com.tugbaolcer.foreignexchangeapp.presentation.screen.splash.SplashScreen
import com.tugbaolcer.foreignexchangeapp.util.Constants.cryptoNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.hubNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.loginNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.onboardingNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.splashNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.stocksNavigationRoute


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navBackStackEntry?.destination

    CustomScaffold(
        bottomBar = {
            if (currentRoute in BottomNav.values().map { it.route }) {
                CustomBottomAppBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        },
        floatingActionButton = {},
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {
        NavHost(
            navController = navController,
            startDestination = splashNavigationRoute
        ) {

            composable(splashNavigationRoute) {
                SplashScreen(navController = navController)
            }

            composable(onboardingNavigationRoute) {
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


            composable(stocksNavigationRoute) { StockScreen() }
            composable(cryptoNavigationRoute) { CryptoScreen() }

            composable(hubNavigationRoute) { HubScreen(navController) }
            composable(loginNavigationRoute) { LoginScreen(navController) }
        }
    }
}
