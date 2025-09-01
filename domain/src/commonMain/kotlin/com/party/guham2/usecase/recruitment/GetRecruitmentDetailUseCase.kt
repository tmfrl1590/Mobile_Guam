package com.party.guham2.usecase.recruitment

import com.party.guham2.repository.RecruitmentRepository

class GetRecruitmentDetailUseCase(
    private val recruitmentRepository: RecruitmentRepository
) {
    suspend operator fun invoke(partyRecruitmentId: Int) = recruitmentRepository.getRecruitmentDetail(partyRecruitmentId)
}