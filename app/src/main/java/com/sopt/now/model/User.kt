package com.sopt.now.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.sopt.now.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @DrawableRes val profileImage: Int = R.drawable.ic_profile,
    val id: String,
    val pw: String,
    val nickname: String,
    val phoneNumber: String,
    val selfDescription: String = "하이요"
) : Parcelable
