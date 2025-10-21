package com.party.guham2.presentation.model.user.party

import com.party.guham2.model.user.party.MyRecruitment
import com.party.guham2.model.user.party.Party1
import com.party.guham2.model.user.party.PartyApplication
import com.party.guham2.model.user.party.PartyRecruitment
import com.party.guham2.model.user.party.PartyType1
import com.party.guham2.model.user.party.Position1

data class MyRecruitmentModel(
    val total: Int,
    val partyApplications: List<PartyApplicationModel>
)

data class PartyApplicationModel(
    val id: Int,
    val message: String,
    val status: String,
    val createdAt: String,
    val partyRecruitment: PartyRecruitmentModel
)

data class PartyRecruitmentModel(
    val id: Int,
    val status: String,
    val position: Position1Model,
    val party: Party1Model
)

data class Position1Model(
    val main: String,
    val sub: String
)

data class Party1Model(
    val id: Int,
    val title: String,
    val image: String?,
    val partyType: PartyType1Model
)

data class PartyType1Model(
    val type: String,
)

fun MyRecruitment.toPresentation(): MyRecruitmentModel = MyRecruitmentModel(
    total = total,
    partyApplications = partyApplications.map { it.toPresentation() }
)

fun PartyApplication.toPresentation(): PartyApplicationModel = PartyApplicationModel(
    id = id,
    message = message,
    status = status,
    createdAt = createdAt,
    partyRecruitment = partyRecruitment.toPresentation()
)


fun PartyRecruitment.toPresentation(): PartyRecruitmentModel = PartyRecruitmentModel(
    id = id,
    status = status,
    position = position.toPresentation(),
    party = party.toPresentation()
)

fun Position1.toPresentation(): Position1Model = Position1Model(
    main = main,
    sub = sub
)

fun Party1.toPresentation(): Party1Model = Party1Model(
    id = id,
    title = title,
    image = image,
    partyType = partyType.toPresentation()
)

fun PartyType1.toPresentation(): PartyType1Model = PartyType1Model(
    type = type
)