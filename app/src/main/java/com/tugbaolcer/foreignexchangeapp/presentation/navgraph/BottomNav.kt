package com.tugbaolcer.foreignexchangeapp.presentation.navgraph

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.util.Constants.cryptoNavigationRoute
import com.tugbaolcer.foreignexchangeapp.util.Constants.stocksNavigationRoute

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    STOCK(
        stocksNavigationRoute,
        R.drawable.ic_borsa_istanbul,
        R.string.stock_title,
    ),
    CRYPTO(
        cryptoNavigationRoute,
        R.drawable.ic_crypto,
        R.string.crypto_title
    ),

}