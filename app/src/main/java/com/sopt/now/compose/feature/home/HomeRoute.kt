package com.sopt.now.compose.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.component.layout.CircleLoadingScreen
import com.sopt.now.compose.component.layout.ErrorScreen
import com.sopt.now.compose.domain.entity.response.ResponseUserList
import com.sopt.now.compose.feature.main.LocalPhoneSizeComposition
import com.sopt.now.compose.feature.main.PhoneSize
import com.sopt.now.compose.util.UiState

@Composable
fun HomeRoute(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val userListPager = viewModel.userListPager.collectAsLazyPagingItems()

    when (state.loadState) {
        UiState.Loading -> CircleLoadingScreen(modifier)
        UiState.Failure -> ErrorScreen(modifier)
        is UiState.Success -> {
            HomeScreen(
                userList = userListPager,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeScreen(
    userList: LazyPagingItems<ResponseUserList.UserData>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(
            when (LocalPhoneSizeComposition.current) {
                PhoneSize.SMALL -> 1
                PhoneSize.MEDIUM -> 2
                PhoneSize.BIG -> 3
            }
        ),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(userList.itemCount) { index ->
            val user = userList[index]
            ProfileView(
                image = user?.avatar.orEmpty(),
                name = user?.first_name + user?.last_name,
                selfDescription = user?.email.orEmpty(),
                fontSize = 15.sp,
                imageModifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            userList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        CircleLoadingScreen()
                    }

                    loadState.append is LoadState.Loading -> {
                        CircleLoadingScreen()
                    }

                    loadState.source.append is LoadState.NotLoading &&
                            loadState.append.endOfPaginationReached -> {
                        HorizontalDivider()
                    }
                }
            }
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
