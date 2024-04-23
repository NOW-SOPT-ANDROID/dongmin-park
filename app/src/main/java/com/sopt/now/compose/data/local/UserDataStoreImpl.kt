package com.sopt.now.compose.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : UserDataStore {
    override var id: String
        get() = dataStore.getString(ID, "").orEmpty()
        set(value) = dataStore.edit { putString(ID, value) }
    override var pw: String
        get() = dataStore.getString(PW, "").orEmpty()
        set(value) = dataStore.edit { putString(PW, value) }
    override var nickname: String
        get() = dataStore.getString(NICKNAME, "").orEmpty()
        set(value) = dataStore.edit { putString(NICKNAME, value) }
    override var juryang: String
        get() = dataStore.getString(JURYANG, "").orEmpty()
        set(value) = dataStore.edit { putString(JURYANG, value) }

    override fun clearInfo() {
        dataStore.edit().clear().commit()
    }

    companion object {
        private const val ID = "id"
        private const val PW = "pw"
        private const val NICKNAME = "nickname"
        private const val JURYANG = "juryang"
    }
}