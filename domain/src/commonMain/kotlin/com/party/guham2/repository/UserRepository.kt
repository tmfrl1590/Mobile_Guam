package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.Login

interface UserRepository {

    // 구글 로그인
    suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<Login, DataError.Remote>

    suspend fun getPositionList(main: String): Result<List<Position>, DataError>

    // 유저 닉네임 중복체크
    suspend fun checkNickName(signupAccessToken: String, nickname: String): Result<String, DataError.Remote>
}