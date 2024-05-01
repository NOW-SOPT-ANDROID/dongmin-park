package com.sopt.now.compose.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.component.layout.CircleLoadingScreen
import com.sopt.now.compose.component.layout.ErrorScreen
import com.sopt.now.compose.domain.entity.response.ResponseUserList
import com.sopt.now.compose.util.UiState
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeRoute(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getUserList()
    }

    when (state.loadState) {
        UiState.Loading -> CircleLoadingScreen(modifier)
        UiState.Failure -> ErrorScreen(modifier)
        is UiState.Success -> {
            val userList = (state.loadState as UiState.Success<ResponseUserList>).data.userList
            HomeScreen(
                userList = userList,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeScreen(
    userList: ImmutableList<ResponseUserList.UserData>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp), // 컨텐츠와 격자 사이의 패딩
        verticalArrangement = Arrangement.spacedBy(8.dp), // 행 간격
        horizontalArrangement = Arrangement.spacedBy(8.dp) // 열 간격
    ) {
        items(userList) { user ->
            ProfileView(
                image = user.avatar,
                name = user.first_name + user.last_name,
                selfDescription = user.email,
                fontSize = 15.sp,
                imageModifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileView(
    image: String,
    name: String,
    selfDescription: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Column(
        modifier = modifier
            .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
            .background(Color.LightGray, shape = RoundedCornerShape(4.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = fontSize
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = selfDescription)
        Spacer(modifier = Modifier.height(4.dp))
        GlideImage(
            model = image,
            contentDescription = "profile",
            contentScale = contentScale,
            modifier = imageModifier
        )
    }
}
