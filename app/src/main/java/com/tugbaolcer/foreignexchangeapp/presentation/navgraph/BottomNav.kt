package com.tugbaolcer.foreignexchangeapp.presentation.navgraph

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tugbaolcer.foreignexchangeapp.R

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    BORSAISTANBUL(
        "borsaIstanbulNavigationRoute",
        R.drawable.ic_borsa_istanbul,
        R.string.borsa_istanbul_title,
    ),
    CRYPTO(
        "cryptoNavigationRoute",
        R.drawable.ic_crypto,
        R.string.crypto_title
    ),

}