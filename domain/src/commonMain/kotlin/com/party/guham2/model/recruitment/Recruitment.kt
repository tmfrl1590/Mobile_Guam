package com.party.guham2.model.recruitment

data class Recruitment(
    val partyRecruitments: List<RecruitmentItem>,
    val total: Int,
)

data class RecruitmentItem(
    val id: Int,
    val recruitingCount: Int,
    val recruitedCount: Int,
    val content: String,
    val createdAt: String,
    val party: RecruitmentParty,
    val position: RecruitmentPosition,
)

data class RecruitmentParty(
    val id: Int,
    val title: String,
    val image: String?,
    val partyType: RecruitmentPartyType,
)

data class RecruitmentPartyType(
    val id: Int,
    val type: String,
)

data class RecruitmentPosition(
    val id: Int,
    val main: String,
    val sub: String,
)
