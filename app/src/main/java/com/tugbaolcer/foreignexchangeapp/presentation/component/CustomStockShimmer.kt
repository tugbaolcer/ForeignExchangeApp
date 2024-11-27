package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.R

@Composable
fun CustomStockShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .shimmerEffect(),
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
                    text = ""
                )

                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(color = Color.LightGray),
                    text = ""
                )
            }

            // Text'leri eşit mesafede dağıtmak için bir düzenleme yapıldı
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = ""
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = ""
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(color = Color.LightGray)
                    .weight(1f),
                text = ""
            )
        }
    }
}


fun Modifier.shimmerEffect(
    shimmerColors: List<Color> = listOf(
        Color(0xFFB8B5B5),
        Color(0xFF8F8B8B),
        Color(0xFFB8B5B5)
    ),
    animationDuration: Int = 1000
): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }

    // Animasyon başlangıç ve bitiş konumları
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing) // Animasyon süresi
        ), label = ""
    )

    // Shimmer için lineer gradyan
    val shimmerBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(startOffsetX, 0f),
        end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
    )

    this
        .background(shimmerBrush) // Arka plan shimmer fırçası
        .onGloballyPositioned { size = it.size } // Boyut takibi
}



@Preview
@Composable
private fun BodyPreview() {
    CustomStockShimmer()
}