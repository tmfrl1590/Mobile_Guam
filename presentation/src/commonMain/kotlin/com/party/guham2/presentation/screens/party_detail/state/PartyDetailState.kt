package com.party.guham2.presentation.screens.party_detail.state

import com.party.guham2.design.component.tab_area.partyDetailTabList
import com.party.guham2.presentation.model.party.PartyDetailModel
import com.party.guham2.presentation.model.user.PartyAuthorityModel

data class PartyDetailState(
    val selectedTabText: String = partyDetailTabList[0],

    // party authority
    val partyAuthority: PartyAuthorityModel = PartyAuthorityModel(),

    // party detail
    val partyDetail: PartyDetailModel = PartyDetailModel()
)