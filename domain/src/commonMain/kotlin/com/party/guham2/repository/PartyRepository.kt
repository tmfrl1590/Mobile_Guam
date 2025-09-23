package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.party.Party
import com.party.guham2.model.party.PartyDetail
import com.party.guham2.model.party.PartyRecruitment
import com.party.guham2.model.party.PartyUsers

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

    // 파티 상세 정보 조회
    suspend fun getPartyDetail(partyId: Int): Result<PartyDetail, DataErrorRemote<Unit>>

    // 파티 상세 정보 조회 - 파티원 리스트 조회
    suspend fun getPartyUsers(partyId: Int, page: Int, limit: Int, sort: String, order: String): Result<PartyUsers, DataErrorRemote<Unit>>

    // 파티 상세 조회 - 모집 공고 리스트 조회
    suspend fun getPartyRecruitmentList(partyId: Int, sort: String, order: String, main: String?, status: String?): Result<List<PartyRecruitment>, DataErrorRemote<Unit>>
}