package com.sopt.now.compose.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.sopt.now.compose.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val juryang: String = "",
    @DrawableRes val profileImage: Int = R.drawable.img_profile,
    val selfDescription: String = "하이요"
) : Parcelable
