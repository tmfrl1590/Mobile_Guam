package com.party.guham2.remote

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.party.PartyDetail
import com.party.guham2.model.party.PartyDetailEntity
import com.party.guham2.model.party.PartyEntity

interface PartyDataSource {

    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<PartyEntity, DataError>

    // 파티 상세 정보 조회
    suspend fun getPartyDetail(partyId: Int): Result<PartyDetailEntity, DataErrorRemote<Unit>>
}