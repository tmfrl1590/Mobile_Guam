package com.party.guham2.repository

import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.core.domain.mapError
import com.party.guham2.local.DataStoreSource
import com.party.guham2.model.user.CheckUserApplicationStatus
import com.party.guham2.model.user.PartyAuthority
import com.party.guham2.model.user.Position
import com.party.guham2.model.user.join.UserSignUp
import com.party.guham2.model.user.join.UserSignUpRequest
import com.party.guham2.model.user.join.toEntity
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.LoginFailure
import com.party.guham2.model.user.login.LoginSuccess
import com.party.guham2.model.user.login.toEntity
import com.party.guham2.model.user.party.MyParty
import com.party.guham2.model.user.party.MyRecruitment
import com.party.guham2.remote.UserDataSource

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val dataStoreSource: DataStoreSource,
): UserRepository{
    override suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<LoginSuccess, DataErrorRemote<LoginFailure>> {
        return userDataSource
            .loginGoogle(accessTokenRequestEntity = accessTokenRequest.toEntity())
            .map { it ->
                dataStoreSource.saveAccessToken(it.accessToken)
                it.toDomain()
            }.mapError { error ->
                when(error){
                    is DataErrorRemote.Unauthorized<*> -> {
                        DataErrorRemote.Unauthorized(
                            response = LoginFailure(
                                message = error.response?.message ?: "",
                                signupAccessToken = error.response?.signupAccessToken ?: "",
                                userEmail = ""
                            )
                        )
                    }
                    else -> {
                        TODO()
                    }
                }
            }
    }


    override suspend fun getPositionList(main: String): Result<List<Position>, DataErrorRemote<Unit>> {
        return userDataSource.getPositionList(main = main).map { it.map { it.toDomain() } }
    }

    override suspend fun checkNickName(
        signupAccessToken: String,
        nickname: String
    ): Result<String, DataErrorRemote<String>> {
        return userDataSource.checkNickName(signupAccessToken = signupAccessToken, nickname = nickname)
    }

    override suspend fun userSignUp(
        signupAccessToken: String,
        userSignUpRequest: UserSignUpRequest
    ): Result<UserSignUp, DataErrorRemote<Unit>> {
        return userDataSource.userSignUp(signupAccessToken = signupAccessToken, userSignUpRequestEntity = userSignUpRequest.toEntity()).map { it.toDomain() }
    }

    override suspend fun getPartyAuthority(partyId: Int): Result<PartyAuthority, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return userDataSource.getPartyAuthority(accessToken = accessToken, partyId = partyId).map { it.toDomain() }
    }

    override suspend fun checkUserApplicationStatus(
        partyId: Int,
        partyRecruitmentId: Int
    ): Result<CheckUserApplicationStatus, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return userDataSource.checkUserApplicationStatus(accessToken = accessToken, partyId = partyId, partyRecruitmentId = partyRecruitmentId).map { it.toDomain() }
    }

    override suspend fun getMyParties(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        status: String?
    ): Result<MyParty, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return userDataSource.getMyParties(accessToken = accessToken, page = page, limit = limit, sort = sort, order = order, status = status).map { it.toDomain() }
    }

    override suspend fun getMyRecruitments(
        page: Int,
        limit: Int,
        sort: String,
        order: String
    ): Result<MyRecruitment, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return userDataSource.getMyRecruitments(accessToken = accessToken, page = page, limit = limit, sort = sort, order = order).map { it.toDomain() }
    }
}