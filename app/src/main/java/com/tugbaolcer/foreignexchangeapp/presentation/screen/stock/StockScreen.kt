package com.tugbaolcer.foreignexchangeapp.presentation.screen.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.data.network.dto.StockDto
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomScaffold
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomStockShimmer
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomTopBar

@Composable
fun StockScreen() {

    val viewModel: StockViewModel = hiltViewModel()
    val viewState = viewModel.uiState.collectAsState().value
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
                titleText = stringResource(id = R.string.stock_title)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (viewState.isLoading) {
                    items(10) {
                        CustomStockShimmer()
                    }
                } else {
                    items(viewState.data!!) { stock ->
                        Content(modifier = Modifier, item = stock)
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background
    )
}

@Composable
fun Content(modifier: Modifier, item: StockDto.StockDtoItem?) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier
                    .padding(start = 10.dp)
                    .clip(shape = RoundedCornerShape(15))
                    .size(30.dp)
                    .background(Color.LightGray),
                painter = painterResource(R.drawable.ic_right_arrow),
                tint = Color.LightGray,
                contentDescription = ""
            )

            Column(
                modifier = modifier
                    .width(90.dp)
                    .padding(start = 10.dp)
                    .fillMaxHeight(),
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .background(color = Color.LightGray),
                    text = item?.code ?: "Null",
                    color = Color.Black
                )

                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(color = Color.LightGray),
                    text = item?.time ?: "Null"
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = "${item?.min}"
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = "${item?.max}"
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = "${item?.hacim}"
            )
        }
    }
}

@Preview
@Composable
fun StockScreenPreview() {
    StockScreen()
}