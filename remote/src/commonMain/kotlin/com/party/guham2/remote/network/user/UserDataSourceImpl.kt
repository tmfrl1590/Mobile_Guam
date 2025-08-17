package com.party.guham2.remote.network.user

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.user.PositionEntity
import com.party.guham2.remote.RemoteConstants.serverUrl
import com.party.guham2.remote.UserDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UserDataSourceImpl(
    private val httpClient: HttpClient
): UserDataSource {
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