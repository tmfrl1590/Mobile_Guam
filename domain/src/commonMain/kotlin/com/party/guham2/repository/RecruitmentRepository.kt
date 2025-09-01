package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import com.party.guham2.model.recruitment.Recruitment
import com.party.guham2.model.recruitment.RecruitmentDetail

interface RecruitmentRepository {

    // 모집공고 리스트
    suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<Recruitment, DataError>

    // 모집공고 상세 조회
    suspend fun getRecruitmentDetail(
        partyRecruitmentId: Int
    ): Result<RecruitmentDetail, DataErrorRemote<Unit>>
}