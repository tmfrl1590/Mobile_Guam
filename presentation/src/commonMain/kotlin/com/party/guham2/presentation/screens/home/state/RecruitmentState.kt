package com.party.guham2.presentation.screens.home.state

import com.party.guham2.design.component.bottomsheet.positionList
import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel
import com.party.guham2.presentation.model.user.PositionItemModel

data class RecruitmentState(
    // 모집공고 리스트
    val recruitmentList: List<RecruitmentItemModel> = emptyList(),

    /*
    * 직무 바텀시트
    * */
    val isShowPositionBottomSheet: Boolean = false,
    val selectedMainPosition: String = positionList[0],
    val mainAndSubPositionList: List<PositionItemModel> = emptyList(),
    val selectedSubPositionList: List<PositionItemModel> = emptyList(),
    val selectedMainAndSubPosition: List<Pair<String, String>> = emptyList(),
    val selectedPositionCount: Int = 0,

    /*
    * 파티 유형 바텀시트
    * */
    val isShowPartyTypeBottomSheet: Boolean = false,
    val selectedPartyTypeList: List<String> = emptyList(),
    val selectedPartyTypeCount: Int = 0,
    val isOnTogglePartySection: Boolean = true, // 진행중 토글
    val isDescPartySection: Boolean = true, // 등록일 순 내림차순
)
