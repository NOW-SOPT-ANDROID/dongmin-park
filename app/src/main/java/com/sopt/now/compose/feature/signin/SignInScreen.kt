package com.sopt.now.compose.feature.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.component.TextFieldWithTitle
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun SignInScreen() {


}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome To SOPT",
                    fontSize = 30.sp
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = 30.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TextFieldWithTitle(title = "ID")
            Spacer(modifier = Modifier.height(30.dp))
            TextFieldWithTitle(title = "PW")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "로그인하기")
                }
            }
        }
    }
}


@Preview
@Composable
fun SignInScreenPreview() {
    NOWSOPTAndroidTheme {
        MainScreen()
    }
}
