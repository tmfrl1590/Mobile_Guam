package com.party.guham2.usecase.party

import com.party.guham2.repository.PartyRepository

class GetPartyDetailUseCase(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(partyId: Int) =
        partyRepository.getPartyDetail(partyId)
}