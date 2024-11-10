package com.tugbaolcer.foreignexchangeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.tugbaolcer.foreignexchangeapp.presentation.cryptos.CryptoScreen
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ForeignExchangeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForeignExchangeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   CryptoScreen(innerPadding)
                }
            }
        }
    }
}
