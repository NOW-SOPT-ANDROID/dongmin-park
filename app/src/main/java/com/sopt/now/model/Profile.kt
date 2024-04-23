package com.sopt.now.model

import androidx.annotation.DrawableRes
import com.sopt.now.R

sealed class Profile {
    abstract val name: String
    abstract val view: Int

    data class MyProfile(
        @DrawableRes val profileImage: Int,
        override val name: String,
        val selfDescription: String,
        override val view: Int = R.layout.item_my,
    ) : Profile()

    data class FriendProfile(
        @DrawableRes val profileImage: Int,
        override val name: String,
        val selfDescription: String,
        override val view: Int = R.layout.item_friend,
    ) : Profile()
}
