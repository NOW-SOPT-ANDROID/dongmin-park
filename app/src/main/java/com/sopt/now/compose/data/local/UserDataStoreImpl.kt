package com.sopt.now.compose.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : UserDataStore {
    override var userId: String
        get() = dataStore.getString(USER_ID, "").orEmpty()
        set(value) = dataStore.edit { putString(USER_ID, value) }
    override var id: String
        get() = dataStore.getString(ID, "").orEmpty()
        set(value) = dataStore.edit { putString(ID, value) }
    override var pw: String
        get() = dataStore.getString(PW, "").orEmpty()
        set(value) = dataStore.edit { putString(PW, value) }
    override var nickname: String
        get() = dataStore.getString(NICKNAME, "").orEmpty()
        set(value) = dataStore.edit { putString(NICKNAME, value) }
    override var phoneNumber: String
        get() = dataStore.getString(PHONE_NUMBER, "").orEmpty()
        set(value) = dataStore.edit { putString(PHONE_NUMBER, value) }

    override fun clearInfo() {
        dataStore.edit().clear().commit()
    }

    companion object {
        private const val USER_ID = "userId"
        private const val ID = "id"
        private const val PW = "pw"
        private const val NICKNAME = "nickname"
        private const val PHONE_NUMBER = "phoneNumber"
    }
}
