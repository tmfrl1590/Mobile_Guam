package com.party.guham2.presentation.screens.party_detail.action

sealed interface PartyDetailAction {
    data class OnClickTab(val tabText: String): PartyDetailAction
}