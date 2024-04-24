package com.sopt.now.compose.feature.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.sopt.now.compose.model.Friend
import com.sopt.now.compose.model.User
import com.sopt.now.compose.util.UiState
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeRoute(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.setState()
    }

    when (state.loadState) {
        UiState.Loading -> CircleLoadingScreen(modifier)
        UiState.Failure -> ErrorScreen(modifier)
        is UiState.Success -> {
            val user = (state.loadState as UiState.Success<User>).data
            HomeScreen(
                user = user,
                friendList = viewModel.friendDataList,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeScreen(
    user: User,
    friendList: ImmutableList<Friend>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            ProfileView(
                user.profileImage,
                user.nickname,
                user.selfDescription,
                fontSize = 25.sp,
                imageModifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )
        }
        items(friendList) { friend ->
            ProfileView(
                friend.profileImage,
                friend.name,
                friend.selfDescription,
                fontSize = 15.sp,
                imageModifier = Modifier
                    .size(30.dp)
                    .aspectRatio(1f)
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileView(
    @DrawableRes image: Int,
    name: String,
    selfDescription: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
            .background(Color.LightGray, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = image,
            contentDescription = "profile",
            contentScale = contentScale,
            modifier = imageModifier
        )
        Column(
            modifier = Modifier.padding(start = 6.dp)
        ) {
            Text(
                text = name,
                fontSize = fontSize
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = selfDescription)
        }
    }
}
