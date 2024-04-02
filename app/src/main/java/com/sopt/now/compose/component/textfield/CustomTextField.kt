package com.sopt.now.compose.component.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    ) { innerTextField ->
        Column {
            Box {
                innerTextField()

                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = textStyle,
                        color = Color.LightGray,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    NOWSOPTAndroidTheme {
        Column {
            CustomTextField(value = "value", onValueChanged = {}, hint = "입력해주세용")
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            CustomTextField(value = "", onValueChanged = {}, hint = "입력해주세용")
        }
    }
}
