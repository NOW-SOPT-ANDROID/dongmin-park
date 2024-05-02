package com.sopt.now.data.local

interface UserDataStore {
    var userId: String
    var id: String
    var pw: String
    var nickname: String
    var phoneNumber: String
    fun clearInfo()
}
