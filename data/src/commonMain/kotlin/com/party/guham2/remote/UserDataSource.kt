package com.party.guham2.remote

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.login.LoginEntity
import com.party.guham2.model.user.PositionEntity
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.AccessTokenRequestEntity

interface UserDataSource {

    suspend fun loginGoogle(accessTokenRequestEntity: AccessTokenRequestEntity): Result<LoginEntity, DataError.Remote>

    suspend fun getPositionList(main: String): Result<List<PositionEntity>, DataError>

    suspend fun checkNickName(signupAccessToken: String, nickname: String): Result<String, DataError.Remote>
}