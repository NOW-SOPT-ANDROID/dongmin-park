package com.sopt.now.compose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val juryang: String = ""
) : Parcelable
