package com.party.guham2.remote.network.user

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.login.LoginEntity
import com.party.guham2.model.user.PositionEntity
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.remote.RemoteConstants.serverUrl
import com.party.guham2.remote.UserDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDataSourceImpl(
    private val httpClient: HttpClient
): UserDataSource {
    override suspend fun loginGoogle(accessTokenRequest: AccessTokenRequest): Result<LoginEntity, DataError> {
        return safeCall<LoginEntity> {
            httpClient.post(
                urlString = serverUrl("api/users/google/app/login")
            ){
                contentType(ContentType.Application.Json) // JSON으로 보낼 때 필수
                setBody(accessTokenRequest)
            }
        }
    }

    override suspend fun getPositionList(main: String): Result<List<PositionEntity>, DataError> {
        return safeCall<List<PositionEntity>> {
            httpClient.get(
                urlString = serverUrl("api/positions")
            ){
                parameter("main", main)
            }
        }
    }
}