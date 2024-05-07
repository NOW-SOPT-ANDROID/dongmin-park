package com.sopt.now.compose.data.remote.repositoryImpl

import com.sopt.now.compose.data.local.UserDataStore
import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.domain.entity.request.RequestSignInEntity
import com.sopt.now.compose.domain.entity.request.RequestUserEntity
import com.sopt.now.compose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val userDataStore: UserDataStore,
) : AuthRepository {
    override suspend fun postSignUp(user: RequestUserEntity): String? =
        runCatching {
            authService.postSignUp(user)
        }.fold(
            onSuccess = {
                it.headers()[HEADER]
            },
            onFailure = {
                null
            }
        )

    override suspend fun postSignIn(user: RequestSignInEntity): String? =
        runCatching {
            authService.postSignIn(user)
        }.fold(
            onSuccess = {
                it.headers()[HEADER]
            },
            onFailure = {
                null
            }
        )

    override fun getId() = userDataStore.id
    override fun getPassword() = userDataStore.password
    override fun getNickname() = userDataStore.nickname
    override fun getPhoneNumber() = userDataStore.phoneNumber
    override fun setId(id: String) {
        userDataStore.id = id
    }

    override fun setPassword(password: String) {
        userDataStore.password = password
    }

    override fun setNickname(nickname: String) {
        userDataStore.nickname = nickname
    }

    override fun setPhoneNumber(phoneNumber: String) {
        userDataStore.phoneNumber = phoneNumber
    }

    override fun setUserId(userId: String) {
        userDataStore.userId = userId
    }

    companion object {
        private const val HEADER = "location"
    }
}
