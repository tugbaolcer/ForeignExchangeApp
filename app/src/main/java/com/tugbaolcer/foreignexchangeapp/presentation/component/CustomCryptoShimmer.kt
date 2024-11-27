package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomCryptoShimmer() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .shimmerEffect(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val commonModifier = Modifier
                .padding(horizontal = 5.dp)
                .background(color = Color.LightGray)
                .weight(1f)

            Text(
                modifier = commonModifier,
                text = ""
            )

            Spacer(modifier = Modifier.weight(2f))

            Text(
                modifier = commonModifier,
                text = ""
            )
        }
    }
}


@Preview
@Composable
private fun BodyPreview() {
    CustomCryptoShimmer()
}