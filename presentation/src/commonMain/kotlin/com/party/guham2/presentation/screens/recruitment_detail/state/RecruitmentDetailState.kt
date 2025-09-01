package com.party.guham2.presentation.screens.recruitment_detail.state

import com.party.guham2.presentation.model.recruitment.RecruitmentDetailModel

data class RecruitmentDetailState(
    val isLoading: Boolean = false,

    val recruitmentDetail: RecruitmentDetailModel = RecruitmentDetailModel(),
)
