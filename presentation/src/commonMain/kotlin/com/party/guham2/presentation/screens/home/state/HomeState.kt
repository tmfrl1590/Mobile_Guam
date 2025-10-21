package com.party.guham2.presentation.screens.home.state

import com.party.guham2.presentation.model.banner.BannerItemModel

data class HomeState(
    // 플로팅 버튼
    val isExpandedFloating: Boolean = false,

    // 배너 리스트
    val bannerList: List<BannerItemModel> = emptyList(),
)
