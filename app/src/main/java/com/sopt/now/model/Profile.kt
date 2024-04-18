package com.sopt.now.model

import androidx.annotation.DrawableRes

sealed class Profile {
    abstract val name: String
    data class MyProfile(
        @DrawableRes val profileImage: Int,
        override val name: String,
        val selfDescription: String
    ) : Profile()

    data class FriendProfile(
        @DrawableRes val profileImage: Int,
        override val name: String,
        val selfDescription: String,
    ) : Profile()
}
