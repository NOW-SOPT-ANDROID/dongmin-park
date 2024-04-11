package com.sopt.now.compose.component.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = R.drawable.img_error,
            contentDescription = "error",
            modifier = Modifier
                .size(100.dp)
        )
        Text(text = "이런! 오류가 발생했어요!")
        Text(text = "다시시도해주세요!")
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    NOWSOPTAndroidTheme {
        ErrorScreen()
    }
}