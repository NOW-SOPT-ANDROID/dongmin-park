package com.sopt.now.compose.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.R
import com.sopt.now.compose.component.text.TextWithTitle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                model = R.drawable.img_profile,
                contentDescription = "profile",
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f)
            )
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "이름"
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Text(text = "자기소개개개개개개개개")

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        TextWithTitle(title = "ID", description = state.id)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = "PW", description = state.pw)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = "Nickname", description = state.nickname)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = "주량", description = state.juryang)
    }
}
