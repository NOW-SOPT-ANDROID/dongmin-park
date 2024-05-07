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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sopt.now.compose.component.layout.CircleLoadingScreen
import com.sopt.now.compose.domain.entity.response.ReqresUserModel
import com.sopt.now.compose.feature.main.DeviceSize
import com.sopt.now.compose.feature.main.LocalDeviceSizeComposition

@Composable
fun HomeRoute(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val userPagingItems: LazyPagingItems<ReqresUserModel> =
        viewModel.userState.collectAsLazyPagingItems()

    HomeScreen(
        userList = userPagingItems,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    userList: LazyPagingItems<ReqresUserModel>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(
            when (LocalDeviceSizeComposition.current) {
                DeviceSize.SMALL -> 1
                DeviceSize.MEDIUM -> 2
                DeviceSize.BIG -> 3
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
                name = user?.firstName + user?.lastName,
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
