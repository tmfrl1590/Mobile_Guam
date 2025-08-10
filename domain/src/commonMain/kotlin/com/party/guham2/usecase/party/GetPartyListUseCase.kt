package com.party.guham2.usecase.party

import com.party.guham2.repository.PartyRepository

class GetPartyListUseCase(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int>,
        titleSearch: String?,
        status: String?
    ) = partyRepository.getPartyList(
        page = page,
        limit = limit,
        sort = sort,
        order = order,
        partyTypes = partyTypes,
        titleSearch = titleSearch,
        status = status
    )
}