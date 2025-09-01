package com.party.guham2.remote.network.recruitment

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.recruitment.RecruitmentDetailEntity
import com.party.guham2.model.recruitment.RecruitmentEntity
import com.party.guham2.remote.RecruitmentDataSource
import com.party.guham2.remote.RemoteConstants.serverUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecruitmentDataSourceImpl(
    private val httpClient: HttpClient
): RecruitmentDataSource {

    // 모집 공고 리스트 조회
    override suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<RecruitmentEntity, DataError> {
        return safeCall<RecruitmentEntity, Unit> {
            httpClient.get(
                urlString = serverUrl("api/parties/recruitments")
            ){
                parameter("page", page)
                parameter("limit", limit)
                parameter("sort", sort)
                parameter("order", order)
                if (partyTypes.isNotEmpty()) {
                    partyTypes.forEach { parameter("partyType", it) }
                }
                titleSearch?.let { parameter("titleSearch", it) }
                if(position.isNotEmpty()){
                    position.forEach { parameter("position", it) }
                }
            }
        }
    }

    override suspend fun getRecruitmentDetail(partyRecruitmentId: Int): Result<RecruitmentDetailEntity, DataErrorRemote<Unit>> {
        return safeCall<RecruitmentDetailEntity, Unit> {
            httpClient.get(
                urlString = serverUrl("api/parties/recruitments/$partyRecruitmentId")
            ){
                parameter("partyRecruitmentId", partyRecruitmentId)
            }
        }
    }
}