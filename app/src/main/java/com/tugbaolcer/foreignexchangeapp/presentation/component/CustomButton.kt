package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BlackTwoColor

@Composable
fun CustomButton(buttonText: String, onCustomClick: () -> Unit ){
    Button(
        onClick = { onCustomClick()  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BlackTwoColor
        ),
        contentPadding = PaddingValues(vertical = 12.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        )
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}