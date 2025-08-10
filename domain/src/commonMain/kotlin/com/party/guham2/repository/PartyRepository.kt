package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.party.Party

interface PartyRepository {

    // 파티 리스트 조회
    suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<Party, DataError>
}