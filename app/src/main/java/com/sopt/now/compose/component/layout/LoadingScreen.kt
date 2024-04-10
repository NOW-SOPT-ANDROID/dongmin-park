package com.sopt.now.compose.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
fun CircleLoadingScreen(modifier: Modifier = Modifier) {
    LoadingScreen(modifier = modifier) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    NOWSOPTAndroidTheme {
        // LoadingScreen()
    }
}