package com.sopt.now.compose.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.R
import com.sopt.now.compose.component.layout.CircleLoadingScreen
import com.sopt.now.compose.component.layout.ErrorScreen
import com.sopt.now.compose.component.text.DescriptionWithTitle
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {

    }

    when (state.loadState) {
        UiState.Loading -> CircleLoadingScreen()
        UiState.Failure -> ErrorScreen()
        is UiState.Success -> {
            val data = (state.loadState as UiState.Success<User>).data
            HomeScreen(
                id = data.id,
                pw = data.pw,
                nickname = data.nickname,
                juryang = data.juryang
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    id: String,
    pw: String,
    nickname: String,
    juryang: String
) {
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
            )
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = stringResource(id = R.string.name)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Text(text = stringResource(id = R.string.description))

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.id), description = id)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.pw), description = pw)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.nickname), description = nickname)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.juryang), description = juryang)
    }
}