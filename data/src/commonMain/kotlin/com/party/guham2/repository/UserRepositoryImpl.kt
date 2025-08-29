package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.local.DataStoreSource
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.Login
import com.party.guham2.model.user.login.LoginSuccess
import com.party.guham2.model.user.login.toEntity
import com.party.guham2.remote.UserDataSource

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val dataStoreSource: DataStoreSource,
): UserRepository{
    override suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<Login, DataError.Remote> {
        return userDataSource
            .loginGoogle(accessTokenRequestEntity = accessTokenRequest.toEntity())
            .map { it.toDomain() }
            .onSuccess { (it as? LoginSuccess)
                ?.let { success -> dataStoreSource.saveAccessToken(accessToken = success.accessToken) }
            }
    }


    override suspend fun getPositionList(main: String): Result<List<Position>, DataError> {
        return userDataSource.getPositionList(main = main).map { it.map { it.toDomain() } }
    }

    override suspend fun checkNickName(
        signupAccessToken: String,
        nickname: String
    ): Result<String, DataError.Remote> {
        return userDataSource.checkNickName(signupAccessToken = signupAccessToken, nickname = nickname)
    }

}