package com.party.guham2.model.user.party

data class MyRecruitment(
    val total: Int,
    val partyApplications: List<PartyApplication>
)

data class PartyApplication(
    val id: Int,
    val message: String,
    val status: String,
    val createdAt: String,
    val partyRecruitment: PartyRecruitment
)

data class PartyRecruitment(
    val id: Int,
    val status: String,
    val position: Position1,
    val party: Party1
)

data class Position1(
    val main: String,
    val sub: String
)

data class Party1(
    val id: Int,
    val title: String,
    val image: String?,
    val partyType: PartyType1
)

data class PartyType1(
    val type: String
)