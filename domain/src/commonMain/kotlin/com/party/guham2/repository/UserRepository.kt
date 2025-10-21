package com.party.guham2.repository

import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.CheckUserApplicationStatus
import com.party.guham2.model.user.PartyAuthority
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.join.UserSignUp
import com.party.guham2.model.user.join.UserSignUpRequest
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.LoginFailure
import com.party.guham2.model.user.login.LoginSuccess
import com.party.guham2.model.user.party.MyParty
import com.party.guham2.model.user.party.MyRecruitment

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

    // 모집공고에 유저가 지원했는지 여부
    suspend fun checkUserApplicationStatus(partyId: Int, partyRecruitmentId: Int): Result<CheckUserApplicationStatus, DataErrorRemote<Unit>>

    // 내 파티 리스트 조회
    suspend fun getMyParties(page: Int, limit: Int, sort: String, order: String, status: String?): Result<MyParty, DataErrorRemote<Unit>>

    // 내 지원목록 리스트 조회
    suspend fun getMyRecruitments(page: Int, limit: Int, sort: String, order: String): Result<MyRecruitment, DataErrorRemote<Unit>>
}