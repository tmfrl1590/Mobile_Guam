package com.party.guham2.presentation.screens.home.action

sealed interface HomeAction {
    data class OnClickTab(val tabText: String) : HomeAction
}