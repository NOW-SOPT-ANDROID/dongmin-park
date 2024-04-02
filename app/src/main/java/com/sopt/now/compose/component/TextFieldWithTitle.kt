package com.sopt.now.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun TextFieldWithTitle(modifier: Modifier = Modifier, title: String) {
    var text by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = title)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
    }
}

@Preview
@Composable
fun TextFieldWithTitlePreview() {
    NOWSOPTAndroidTheme {
        TextFieldWithTitle(title = "title")
    }
}