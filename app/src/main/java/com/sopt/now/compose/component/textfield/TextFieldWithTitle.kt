package com.sopt.now.compose.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun TextFieldWithTitle(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        CustomTextField(
            value = value,
            onValueChanged = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = hint,
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = textStyle,
            keyboardType = keyboardType
        )
    }
}

@Preview
@Composable
fun TextFieldWithTitlePreview() {
    NOWSOPTAndroidTheme {
        Column {
            TextFieldWithTitle(
                title = "hint 나오지롱",
                value = "",
                onValueChanged = {},
                hint = "value 없지롱"
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            TextFieldWithTitle(
                title = "value 나오지롱",
                value = "value 있지롱",
                onValueChanged = {},
                hint = "value 있지롱"
            )
        }

    }
}