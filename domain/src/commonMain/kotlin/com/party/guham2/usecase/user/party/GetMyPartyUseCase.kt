package com.party.guham2.usecase.user.party

import com.party.guham2.repository.UserRepository

class GetMyPartyUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(page: Int, limit: Int, sort: String, order: String, status: String?) = userRepository.getMyParties(page, limit, sort, order, status)
}