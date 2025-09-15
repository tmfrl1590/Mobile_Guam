package com.party.guham2.presentation.model.party

import com.party.guham2.model.party.PartyDetail
import com.party.guham2.model.party.PartyType

data class PartyDetailModel(
    val id: Int = 0,
    val partyType: PartyTypeModel = PartyTypeModel(),
    val title: String = "",
    val content: String = "",
    val image: String?= null,
    val status: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)

data class PartyTypeModel(
    val id: Int = 0,
    val type: String = ""
)

fun PartyDetail.toPresentation(): PartyDetailModel = PartyDetailModel(
    id = id,
    partyType = partyType.toPresentation(),
    title = title,
    content = content,
    image = image,
    status = status,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun PartyType.toPresentation(): PartyTypeModel = PartyTypeModel(
    id = id,
    type = type
)