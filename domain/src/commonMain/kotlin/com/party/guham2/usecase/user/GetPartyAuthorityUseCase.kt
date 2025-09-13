package com.party.guham2.usecase.user

import com.party.guham2.repository.UserRepository

class GetPartyAuthorityUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(partyId: Int) = userRepository.getPartyAuthority(partyId = partyId)
}