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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.R
import com.sopt.now.compose.User
import com.sopt.now.compose.component.text.TextWithTitle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        navController.previousBackStackEntry?.savedStateHandle?.run {
            val user = get<User>("user") ?: User()
            viewModel.setInfo(user)
        }
    }

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
                text = stringResource(id = R.string.name)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Text(text = stringResource(id = R.string.description))

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        TextWithTitle(title = stringResource(id = R.string.id), description = state.id)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = stringResource(id = R.string.pw), description = state.pw)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = stringResource(id = R.string.nickname), description = state.nickname)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        TextWithTitle(title = stringResource(id = R.string.juryang), description = state.juryang)
    }
}
