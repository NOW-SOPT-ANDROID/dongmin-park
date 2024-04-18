package com.sopt.now.presentation.main

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.model.Profile
import com.sopt.now.model.User

class MainViewModel : ViewModel() {
    lateinit var userInfo: User

    fun setUserInfoValue(user: User) {
        userInfo = user
    }

    val mockProfileList = listOf<Profile>(
        Profile.MyProfile(
            profileImage = R.drawable.ic_profile,
            name = "박동민",
            selfDescription = "하이요"
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
    )
}
