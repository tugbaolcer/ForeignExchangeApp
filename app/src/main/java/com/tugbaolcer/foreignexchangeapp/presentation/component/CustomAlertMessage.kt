package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertMessage(
    isDisplayed: Boolean,
    title: String,
    icon: Painter,
    onDismiss: () -> Unit
) {
    if (isDisplayed) {

        BasicAlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)),
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
                    verticalAlignment = Alignment.CenterVertically // Dikeyde merkezleme
                ) {
                    // Icon
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 8.dp) // İkon ve metin arasına boşluk
                    )

                    // Text
                    Text(
                        text = title,
                        modifier = Modifier.weight(1f) // Gerekirse metni sığdırmak için
                    )

                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        contentPadding = PaddingValues(0.dp), // İç boşlukları sıfırla
                        modifier = Modifier
                            .size(32.dp) // Kare boyut
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp) // İkon boyutunu ayarla
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun CustomAlertMessageDialog() {
    CustomAlertMessage(
        isDisplayed = true,
        title = "Delete Item",
        icon = painterResource(id = R.drawable.ic_warning),
        onDismiss = { false }
    )
}
