package com.party.guham2.usecase.party

import com.party.guham2.repository.PartyRepository

class GetPartyRecruitmentUseCase (
    private val partyRepository: PartyRepository,
) {
    suspend operator fun invoke(partyId: Int, sort: String, order: String, main: String?, status: String?) = partyRepository.getPartyRecruitmentList(partyId = partyId, sort = sort, order = order, main = main, status = status)
}