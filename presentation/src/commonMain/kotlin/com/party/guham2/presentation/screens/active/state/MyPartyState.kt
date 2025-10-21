package com.party.guham2.presentation.screens.active.state

import com.party.guham2.design.component.tab_area.stateTabList
import com.party.guham2.presentation.model.user.party.MyPartyModel
import com.party.guham2.presentation.model.user.party.MyRecruitmentModel

data class MyPartyState(
    val selectedTabText: String = stateTabList[0],

    // 내 파티
    val selectedStatus: String = "전체",
    val isMyPartyLoading: Boolean = true,
    val myPartyList: MyPartyModel = MyPartyModel(total = 0, partyUsers = emptyList()),
    val orderByDesc: Boolean = true,

    // 내 지원목록
    val selectedRecruitmentStatus: String = "전체",
    val isShowHelpCard: Boolean = false,
    val orderByRecruitmentDateDesc: Boolean = true,
    val isMyRecruitmentLoading: Boolean = true,
    val myRecruitmentList: MyRecruitmentModel = MyRecruitmentModel(total = 0, partyApplications = emptyList()),
)

