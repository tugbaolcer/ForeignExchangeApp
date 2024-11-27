package com.tugbaolcer.foreignexchangeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tugbaolcer.foreignexchangeapp.presentation.navgraph.NavGraph
import com.tugbaolcer.foreignexchangeapp.presentation.screen.stocks.StockScreen
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ForeignExchangeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForeignExchangeAppTheme {
              NavGraph()
//                StockScreen()
            }
        }
    }
}
