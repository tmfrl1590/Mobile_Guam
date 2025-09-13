package com.party.guham2.presentation.screens.recruitment_detail.state

import com.party.guham2.presentation.model.recruitment.RecruitmentDetailModel
import com.party.guham2.presentation.model.user.PartyAuthorityModel

data class RecruitmentDetailState(
    val isLoading: Boolean = false,

    // 모집공고 상세정보
    val recruitmentDetail: RecruitmentDetailModel = RecruitmentDetailModel(),

    // party authority
    val partyAuthority: PartyAuthorityModel = PartyAuthorityModel()
)
