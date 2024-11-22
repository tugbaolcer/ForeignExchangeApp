package com.tugbaolcer.foreignexchangeapp.presentation.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(
    pageCount: Int = 3,
    currentPage: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            repeat(pageCount) { index ->
                if (index == currentPage) {
                    // Seçili olan gösterge (tamamen mor)
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(color = Color(0xFF6C63FF), shape = CircleShape)
                    )
                } else {
                    // Seçili olmayan göstergeler (kenarları beyaz, içi siyah)
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .border(width = 2.dp, color = Color.White, shape = CircleShape)
                            .background(color = Color.Black, shape = CircleShape)
                    )
                }
            }
        }
    }

}

