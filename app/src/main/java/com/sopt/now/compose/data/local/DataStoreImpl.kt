package com.sopt.now.compose.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class DataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : DataStore {
    override var id: String
        get() = dataStore.getString(ID, "") ?: ""
        set(value) = dataStore.edit { putString(ID, value) }
    override var pw: String
        get() = dataStore.getString(PW, "") ?: ""
        set(value) = dataStore.edit { putString(PW, value) }
    override var nickname: String
        get() = dataStore.getString(NICKNAME, "") ?: ""
        set(value) = dataStore.edit { putString(NICKNAME, value) }
    override var juryang: String
        get() = dataStore.getString(JURYANG, "") ?: ""
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