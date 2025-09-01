package com.party.guham2.remote

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.recruitment.RecruitmentDetailEntity
import com.party.guham2.model.recruitment.RecruitmentEntity

interface RecruitmentDataSource {

    suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<RecruitmentEntity, DataError>

    suspend fun getRecruitmentDetail(
        partyRecruitmentId: Int
    ): Result<RecruitmentDetailEntity, DataErrorRemote<Unit>>
}