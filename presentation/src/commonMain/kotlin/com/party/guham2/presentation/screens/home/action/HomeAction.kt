package com.party.guham2.presentation.screens.home.action

sealed interface HomeAction {
    data class OnClickTab(val tabText: String) : HomeAction

    // 파티탭
    data class OnShowPartyTypeBottomSheet(val isShow: Boolean) : HomeAction
    data class OnSelectPartyType(val partyType: String) : HomeAction
    data object OnResetPartyType : HomeAction
    data object OnApplyPartyType : HomeAction
    data class OnTogglePartySection(val isActive: Boolean) : HomeAction
    data class OnDescPartySection(val isDesc: Boolean) : HomeAction
}