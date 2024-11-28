package com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.data.network.dto.CryptoDto
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.crypto.CryptoViewState
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomCryptoShimmer
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomScaffold
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTopBar
import com.tugbaolcer.foreignexchangeapp.presentation.screen.stocks.StockViewEvent

@Composable
fun CryptoScreen() {
    val viewModel: CryptoViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is StockViewEvent.SnackBarError -> {
                    snackbarHostState.showSnackbar(event.message ?: "Bir hata oluştu")
                }
            }
        }
    }

    CustomScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                titleText = stringResource(id = R.string.crypto_title),
                navigationIcon = null
            )
        },
        content = { paddingValues ->
            CryptoListContent(state, paddingValues)
        },
        bottomBar = {},
        backgroundColor = MaterialTheme.colorScheme.background
    )


}

@Composable
fun CryptoListContent(state: CryptoViewState, paddingValues: PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.Gray)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                items(10) {
                    CustomCryptoShimmer()
                }
            } else {
                items(state.data!!) { crypto ->
                    CryptoContent(crypto = crypto)
                    HorizontalDivider()
                }
            }

        }
    }
}

@Composable
fun CryptoContent(crypto: CryptoDto.Result) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Gray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = crypto.code,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal
        )

        Text(
            text = crypto.pricestr + " " + crypto.currency,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview
@Composable
fun CryptoScreenPreview(){
    val dummyData = CryptoDto.Result(
        changeDay = 20.5,
        changeDaystr = "",
        changeHourstr = "",
        changeHour = 33.3,
        changeWeek = 20.6,
        changeWeekstr = "",
        circulatingSupply = "",
        code = "TEST",
        currency = "",
        marketCap = 99.9,
        marketCapstr = "",
        name = "",
        price = 88.8,
        pricestr = "",
        volume = 99.9,
        volumestr = ""
    )
    CryptoContent(dummyData)
}