package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tugbaolcer.foreignexchangeapp.presentation.navgraph.BottomNav
import com.tugbaolcer.foreignexchangeapp.util.Constants.cryptoNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.stocksNavigationRoute


@Composable
fun CustomBottomAppBar(
    navController: NavController,
    currentDestination: NavDestination?,
) {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ),
        containerColor = Color.White,
        tonalElevation = 10.dp,

        ) {
        BottomNav.values().forEach { screen ->
            val selected = currentDestination.isBottomNavDestinationInHierarchy(screen)
            val primaryColor = MaterialTheme.colorScheme.primary
            val secondaryColor = MaterialTheme.colorScheme.secondary

            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = {
                    Text(
                        text = stringResource(
                            id = screen.titleTextId
                        ),
                        color = if (selected) primaryColor else secondaryColor,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                selected = selected,
                onClick = {
                    navigateToBottomNavDestination(screen, navController)
                }
            )
        }
    }
}

fun navigateToBottomNavDestination(bottomNav: BottomNav, navController: NavController) {
    trace(sectionName = "Navigation: ${bottomNav.name}") {
        when (bottomNav) {
            BottomNav.STOCK -> navController.navigate(stocksNavigationRoute)
            BottomNav.CRYPTO -> navController.navigate(cryptoNavigationRoute)
        }
    }
}

private fun NavDestination?.isBottomNavDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
