package com.party.guham2.presentation.screens.recruitment_detail.action

sealed interface RecruitmentDetailAction {
    data object OnNavigationBack: RecruitmentDetailAction
    data object OnApply: RecruitmentDetailAction
}