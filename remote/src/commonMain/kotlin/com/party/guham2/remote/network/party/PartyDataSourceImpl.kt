package com.party.guham2.remote.network.party

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.party.PartyEntity
import com.party.guham2.remote.PartyDataSource
import com.party.guham2.remote.RemoteConstants.serverUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PartyDataSourceImpl(
    private val httpClient: HttpClient
): PartyDataSource {

    // 파티 리스트 조회
    override suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyEntity, DataError> {
        return safeCall<PartyEntity> {
            httpClient.get(
                urlString = serverUrl("api/parties")
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                if (partyTypes.isNotEmpty()) {
                    partyTypes.forEach { parameter("partyType", it) }
                }
                titleSearch?.let { parameter("titleSearch", it) }
                status?.let { parameter("status", it) }
            }
        }
    }
}