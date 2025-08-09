package com.party.guham2.usecase.recruitment

import com.party.guham2.repository.RecruitmentRepository

class GetRecruitmentListUseCase(
    private val recruitmentRepository: RecruitmentRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int>,
        position: List<Int>,
    )= recruitmentRepository.getRecruitmentList(page = page, limit = limit, sort = sort, order = order, titleSearch = titleSearch, partyTypes = partyTypes, position = position)

}