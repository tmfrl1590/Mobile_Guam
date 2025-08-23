package com.party.guham2.presentation.screens.home.state

import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.model.banner.BannerItemModel

data class HomeState(
    // 라운지 / 파티 / 모집공고 탭
    val selectedTabText: String = homeTopTabList[0],

    // 플로팅 버튼
    val isExpandedFloating: Boolean = false,

    // 배너 리스트
    val bannerList: List<BannerItemModel> = emptyList(),
)
