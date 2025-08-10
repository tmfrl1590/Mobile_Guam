package com.party.guham2.presentation.screens.home.state

import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.model.banner.BannerItemModel
import com.party.guham2.presentation.model.party.PartyItemModel
import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel

data class HomeState(
    // 라운지 / 파티 / 모집공고 탭
    val selectedTabText: String = homeTopTabList[0],

    // 배너 리스트
    val bannerList: List<BannerItemModel> = emptyList(),

    // 신규 모집공고 리스트
    val recruitmentList: List<RecruitmentItemModel> = emptyList(),

    // 신규 파티 리스트
    val partyList: List<PartyItemModel> = emptyList(),
)
