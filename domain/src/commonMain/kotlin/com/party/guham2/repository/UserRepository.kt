package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.Login

interface UserRepository {

    // 구글 로그인
    suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<Login, DataError>

    suspend fun getPositionList(main: String): Result<List<Position>, DataError>
}