package com.party.guham2.presentation.screens.home.action

sealed interface HomeRecruitmentAction {
    // 직무 bottom sheet
    data class OnShowPositionBottomSheet(val isShow: Boolean) : HomeRecruitmentAction
    data class OnSelectMainPosition(val mainPosition: String) : HomeRecruitmentAction
    data class OnSelectSubPosition(val subPosition: String) : HomeRecruitmentAction
    data class OnDeleteSelectedMainAndSubPosition(val position: Pair<String, String>): HomeRecruitmentAction
    data object OnResetSelectedMainAndSubPosition: HomeRecruitmentAction
    data object OnApplyMainAndSubPosition: HomeRecruitmentAction

    // 파티 유형 bottom sheet
    data class OnShowPartyTypeBottomSheet(val isShow: Boolean) : HomeRecruitmentAction
    data class OnSelectPartyType(val partyType: String) : HomeRecruitmentAction
    data object OnResetPartyType : HomeRecruitmentAction
    data object OnApplyPartyType : HomeRecruitmentAction
    data class OnDescPartySection(val isDesc: Boolean) : HomeRecruitmentAction
}