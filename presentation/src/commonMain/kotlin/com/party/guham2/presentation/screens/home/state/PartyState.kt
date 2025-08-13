package com.party.guham2.presentation.screens.home.state

import com.party.guham2.presentation.model.party.PartyItemModel

data class PartyState(

    // 신규 파티 리스트
    val partyList: List<PartyItemModel> = emptyList(),
    /*
    * PartyTab
    * */
    val isShowPartyTypeBottomSheet: Boolean = false,
    val selectedPartyTypeList: List<String> = emptyList(),
    val selectedPartyTypeCount: Int = 0,
    val isOnTogglePartySection: Boolean = true, // 진행중 토글
    val isDescPartySection: Boolean = true, // 등록일 순 내림차순
)
