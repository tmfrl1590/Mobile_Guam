package com.party.guham2.repository

import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.PartyAuthority
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.join.UserSignUp
import com.party.guham2.model.user.join.UserSignUpRequest
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.LoginFailure
import com.party.guham2.model.user.login.LoginSuccess

interface UserRepository {

    // 구글 로그인
    suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<LoginSuccess, DataErrorRemote<LoginFailure>>

    suspend fun getPositionList(main: String): Result<List<Position>, DataErrorRemote<Unit>>

    // 유저 닉네임 중복체크
    suspend fun checkNickName(signupAccessToken: String, nickname: String): Result<String, DataErrorRemote<String>>

    // 유저 회원가입
    suspend fun userSignUp(signupAccessToken: String, userSignUpRequest: UserSignUpRequest): Result<UserSignUp, DataErrorRemote<Unit>>

    // 유저의 파티권한 체크
    suspend fun getPartyAuthority(partyId: Int): Result<PartyAuthority, DataErrorRemote<Unit>>
}