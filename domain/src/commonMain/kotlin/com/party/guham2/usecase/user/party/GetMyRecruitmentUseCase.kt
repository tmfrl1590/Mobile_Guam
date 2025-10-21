package com.party.guham2.usecase.user.party

import com.party.guham2.repository.UserRepository

class GetMyRecruitmentUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
    ) = userRepository.getMyRecruitments(page, limit, sort, order)
}