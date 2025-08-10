package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.model.party.Party
import com.party.guham2.remote.PartyDataSource

class PartyRepositoryImpl(
    private val partyDataSource: PartyDataSource
): PartyRepository {

    // 파티 리스트
    override suspend fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ): Result<Party, DataError> {
        return partyDataSource.getPartyList(
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            partyTypes = partyTypes,
            titleSearch = titleSearch,
            status = status
        ).map { it.toDomain() }
    }
}