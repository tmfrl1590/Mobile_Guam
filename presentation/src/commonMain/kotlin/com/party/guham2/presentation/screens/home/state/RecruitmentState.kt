package com.party.guham2.presentation.screens.home.state

import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel

data class RecruitmentState(
    // 모집공고 리스트
    val recruitmentList: List<RecruitmentItemModel> = emptyList(),
)
