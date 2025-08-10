package com.party.guham2.presentation.model.party

import com.party.guham2.model.party.PartyItem
import com.party.guham2.model.party.PartyTypeItem

data class PartyModel(
    val partyList: List<PartyItemModel>
)

data class PartyItemModel(
    val id: Int,
    val partyType: PartyTypeItem,
    val title: String,
    val content: String,
    val image: String? = null,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val recruitmentCount: Int,
)

fun PartyItem.toPresentation(): PartyItemModel = PartyItemModel(
    id = id,
    partyType = partyType,
    title = title,
    content = content,
    image = image,
    status = status,
    createdAt = createdAt,
    updatedAt = updatedAt,
    recruitmentCount = recruitmentCount,
)