package com.party.guham2.usecase.party

import com.party.guham2.repository.PartyRepository

class GetPartyUsersUseCase (
    private val partyRepository: PartyRepository,
) {
    suspend operator fun invoke(partyId: Int, page: Int, limit: Int, sort: String, order: String) = partyRepository.getPartyUsers(partyId, page, limit, sort, order)
}