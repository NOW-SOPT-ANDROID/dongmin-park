package com.sopt.now.compose.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionWithTitle(title: String, description: String) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = description,
            fontWeight = FontWeight.Light,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun TextWithTitlePreview() {
    DescriptionWithTitle("ID", "chattymin")
}
