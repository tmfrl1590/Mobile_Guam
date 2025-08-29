package com.party.guham2.remote.network.user

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.PositionEntity
import com.party.guham2.model.user.login.AccessTokenRequestEntity
import com.party.guham2.model.user.login.LoginFailureEntity
import com.party.guham2.model.user.login.LoginSuccessEntity
import com.party.guham2.remote.RemoteConstants.serverUrl
import com.party.guham2.remote.UserDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDataSourceImpl(
    private val httpClient: HttpClient
): UserDataSource {
    override suspend fun loginGoogle(accessTokenRequestEntity: AccessTokenRequestEntity): Result<LoginSuccessEntity, DataErrorRemote<LoginFailureEntity>> {
        return safeCall<LoginSuccessEntity, LoginFailureEntity> {
            httpClient.post(
                urlString = serverUrl("api/users/google/app/login")
            ){
                contentType(ContentType.Application.Json) // JSON으로 보낼 때 필수
                setBody(accessTokenRequestEntity)
            }
        }
    }

    override suspend fun getPositionList(main: String): Result<List<PositionEntity>, DataErrorRemote<Unit>> {
        return safeCall<List<PositionEntity>, Unit> {
            httpClient.get(
                urlString = serverUrl("api/positions")
            ){
                parameter("main", main)
            }
        }
    }

    // 유저 닉네임 중복체크
    override suspend fun checkNickName(
        signupAccessToken: String,
        nickname: String
    ): Result<String, DataErrorRemote<String>> {
        return safeCall<String, String> {
            httpClient.get(
                urlString = serverUrl("api/users/check-nickname")
            ){
                headers {
                    append("Authorization", "Bearer $signupAccessToken")
                }
                parameter("nickname", nickname)
            }
        }

    }
}