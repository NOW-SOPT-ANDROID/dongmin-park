package com.sopt.now.compose.feature.my

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
fun MyRoute(
    paddingValues: PaddingValues,
    viewModel: MyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.setState()
    }

    when (state.loadState) {
        UiState.Loading -> CircleLoadingScreen()
        UiState.Failure -> ErrorScreen()
        is UiState.Success -> {
            val user = (state.loadState as UiState.Success<User>).data
            MyScreen(
                profileImage = user.profileImage,
                id = user.id,
                pw = user.pw,
                nickname = user.nickname,
                juryang = user.juryang,
                selfDescription = user.selfDescription,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyScreen(
    @DrawableRes profileImage: Int,
    id: String,
    pw: String,
    nickname: String,
    juryang: String,
    selfDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                model = profileImage,
                contentDescription = "profile",
                modifier = Modifier
                    .size(40.dp)
            )
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = nickname
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Text(text = selfDescription)

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.id), description = id)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.pw), description = pw)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        DescriptionWithTitle(title = stringResource(id = R.string.juryang), description = juryang)
    }
}