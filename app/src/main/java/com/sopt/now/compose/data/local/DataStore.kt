package com.sopt.now.compose.data.local

interface DataStore {
    var id: String
    var pw: String
    var nickname: String
    var juryang: String
    fun clearInfo()
}