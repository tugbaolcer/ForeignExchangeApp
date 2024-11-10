package com.tugbaolcer.foreignexchangeapp.presentation.cryptos

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tugbaolcer.foreignexchangeapp.presentation.cryptos.components.CryptoItem

@Composable
fun CryptoScreen(
    paddingValues: PaddingValues,
    viewModel: CryptoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.Gray)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            if (state.crypto.isNotEmpty()){
                items(state.crypto) { crypto ->
                    CryptoItem(crypto = crypto)
                    HorizontalDivider()
                }
            } else {
               Log.d("LOG_DEBUG", "Liste Boş")
            }

        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error ?:  "Yükleniyor...",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),)
        }
    }
}