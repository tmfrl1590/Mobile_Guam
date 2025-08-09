package com.party.guham2.presentation.model.recruitment

import com.party.guham2.model.recruitment.RecruitmentItem
import com.party.guham2.model.recruitment.RecruitmentParty
import com.party.guham2.model.recruitment.RecruitmentPosition

data class RecruitmentModel(
    val recruitmentList: List<RecruitmentItemModel>
)

data class RecruitmentItemModel(
    val id: Int,
    val recruitingCount: Int,
    val recruitedCount: Int,
    val content: String,
    val createdAt: String,
    val party: RecruitmentParty,
    val position: RecruitmentPosition,
)

fun RecruitmentItem.toPresentation(): RecruitmentItemModel = RecruitmentItemModel(
    id = id,
    recruitingCount = recruitingCount,
    recruitedCount = recruitedCount,
    content = content,
    createdAt = createdAt,
    party = party,
    position = position
)