package com.party.guham2.presentation.screens.party_detail.state

import com.party.guham2.design.component.tab_area.partyDetailTabList
import com.party.guham2.presentation.model.party.PartyDetailModel
import com.party.guham2.presentation.model.party.PartyRecruitmentModel
import com.party.guham2.presentation.model.party.PartyUserModel
import com.party.guham2.presentation.model.party.PartyUsersModel
import com.party.guham2.presentation.model.user.PartyAuthorityModel

data class PartyDetailState(
    val selectedTabText: String = partyDetailTabList[0],

    // party authority
    val partyAuthority: PartyAuthorityModel = PartyAuthorityModel(),

    // party detail
    val partyDetail: PartyDetailModel = PartyDetailModel(),

    // party users
    val partyUsers: PartyUsersModel = PartyUsersModel(),

    // party recruitment
    val isLoadingPartyRecruitment: Boolean = false,
    val partyRecruitmentList: List<PartyRecruitmentModel> = emptyList(),
    val isOnToggle: Boolean = true, // 진행중 토글
    val selectedPosition: String = "전체", // 메인 포지션 필터
    val isShowPositionFilter: Boolean = false,
)