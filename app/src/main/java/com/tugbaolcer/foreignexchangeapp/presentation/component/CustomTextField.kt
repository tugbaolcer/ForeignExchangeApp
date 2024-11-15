package com.tugbaolcer.foreignexchangeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ContainerColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.UnSelectedColor

@Composable
fun CustomTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
) {
    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = ContainerColor,
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = UnSelectedColor
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        colors = textFieldColors,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp),
        singleLine = singleLine
    )
}
