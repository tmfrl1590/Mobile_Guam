package com.party.guham2.repository

import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.map
import com.party.guham2.model.recruitment.Recruitment
import com.party.guham2.remote.RecruitmentDataSource

class RecruitmentRepositoryImpl(
    private val recruitmentDataSource: RecruitmentDataSource
): RecruitmentRepository {

    // 모집공고 리스트
    override suspend fun getRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>
    ): Result<Recruitment, DataError> {
        return recruitmentDataSource.getRecruitmentList(
            page, limit, sort, order, titleSearch, partyTypes, position
        ).map { it.toDomain() }
    }
}