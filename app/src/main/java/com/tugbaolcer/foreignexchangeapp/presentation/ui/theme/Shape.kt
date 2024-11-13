package com.tugbaolcer.foreignexchangeapp.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)

)

val BottomCardShape = Shapes(
    large = RoundedCornerShape(topStart = 80.dp)
)

val RoundedCornerCard = Shapes(
    large = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 0.dp)
)