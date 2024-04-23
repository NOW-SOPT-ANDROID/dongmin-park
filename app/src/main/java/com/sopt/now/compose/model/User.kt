package com.sopt.now.compose.model

import androidx.annotation.DrawableRes
import com.sopt.now.compose.R

data class User(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val juryang: String = "",
    @DrawableRes val profileImage: Int = R.drawable.img_profile,
    val selfDescription: String = "하이요"
) {
    fun isEmptyUser() = id.isEmpty() || pw.isEmpty() || nickname.isEmpty() || juryang.isEmpty()
}
