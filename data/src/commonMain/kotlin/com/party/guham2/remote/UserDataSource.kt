package com.party.guham2.remote

import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.CheckUserApplicationStatus
import com.party.guham2.model.user.CheckUserApplicationStatusEntity
import com.party.guham2.model.user.PartyAuthorityEntity
import com.party.guham2.model.user.PositionEntity
import com.party.guham2.model.user.join.UserSignUpEntity
import com.party.guham2.model.user.join.UserSignUpRequestEntity
import com.party.guham2.model.user.login.AccessTokenRequestEntity
import com.party.guham2.model.user.login.LoginFailureEntity
import com.party.guham2.model.user.login.LoginSuccessEntity

interface UserDataSource {

    suspend fun loginGoogle(accessTokenRequestEntity: AccessTokenRequestEntity): Result<LoginSuccessEntity, DataErrorRemote<LoginFailureEntity>>

    suspend fun getPositionList(main: String): Result<List<PositionEntity>, DataErrorRemote<Unit>>

    suspend fun checkNickName(signupAccessToken: String, nickname: String): Result<String, DataErrorRemote<String>>

    suspend fun userSignUp(signupAccessToken: String, userSignUpRequestEntity: UserSignUpRequestEntity): Result<UserSignUpEntity, DataErrorRemote<Unit>>

    suspend fun getPartyAuthority(accessToken: String, partyId: Int): Result<PartyAuthorityEntity, DataErrorRemote<Unit>>

    suspend fun checkUserApplicationStatus(accessToken: String, partyId: Int, partyRecruitmentId: Int): Result<CheckUserApplicationStatusEntity, DataErrorRemote<Unit>>
}