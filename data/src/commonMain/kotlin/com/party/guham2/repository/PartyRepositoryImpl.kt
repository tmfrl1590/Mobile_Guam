package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.local.DataStoreSource
import com.party.guham2.model.party.Party
import com.party.guham2.model.party.PartyDetail
import com.party.guham2.model.party.PartyRecruitment
import com.party.guham2.model.party.PartyUsers
import com.party.guham2.remote.PartyDataSource

class PartyRepositoryImpl(
    private val partyDataSource: PartyDataSource,
    private val dataStoreSource: DataStoreSource,
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

    override suspend fun getPartyDetail(partyId: Int): Result<PartyDetail, DataErrorRemote<Unit>> {
        return partyDataSource.getPartyDetail(
            partyId = partyId
        ).map { it.toDomain() }
    }

    override suspend fun getPartyUsers(
        partyId: Int,
        page: Int,
        limit: Int,
        sort: String,
        order: String
    ): Result<PartyUsers, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return partyDataSource.getPartyUsers(
            partyId = partyId,
            page = page,
            limit = limit,
            sort = sort,
            order = order,
            accessToken = accessToken,
        ).map { it.toDomain() }
    }

    override suspend fun getPartyRecruitmentList(
        partyId: Int,
        sort: String,
        order: String,
        main: String?,
        status: String?
    ): Result<List<PartyRecruitment>, DataErrorRemote<Unit>> {
        val accessToken = dataStoreSource.getAccessToken() ?: ""
        return partyDataSource.getPartyRecruitmentList(
            partyId = partyId,
            sort = sort,
            order = order,
            main = main,
            status = status,
            accessToken = accessToken
        ).map { it.map { it.toDomain() } }
    }
}