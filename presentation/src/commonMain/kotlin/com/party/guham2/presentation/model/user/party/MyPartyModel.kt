package com.party.guham2.presentation.model.user.party

import com.party.guham2.model.user.party.MyParty
import com.party.guham2.model.user.party.Party
import com.party.guham2.model.user.party.PartyType
import com.party.guham2.model.user.party.PartyUser
import com.party.guham2.model.user.party.Position


data class MyPartyModel(
    val total: Int,
    val partyUsers: List<PartyUserModel>
)

data class PartyUserModel(
    val id: Int,
    val createdAt: String,
    val position: PositionModel,
    val party: PartyModel
)

data class PositionModel(
    val main: String,
    val sub: String
)

data class PartyModel(
    val id: Int,
    val title: String,
    val image: String?,
    val status: String,
    val partyType: PartyTypeModel
)
data class PartyTypeModel(
    val type: String,
)



fun MyParty.toPresentation(): MyPartyModel = MyPartyModel(
    total = total,
    partyUsers = partyUsers.map { it.toPresentation() }
)

fun PartyUser.toPresentation(): PartyUserModel = PartyUserModel(
    id = id,
    createdAt = createdAt,
    position = position.toPresentation(),
    party = party.toPresentation(),
)
fun Position.toPresentation(): PositionModel = PositionModel(
    main = main,
    sub = sub,
)

fun Party.toPresentation(): PartyModel = PartyModel(
    id = id,
    title = title,
    image = image,
    status = status,
    partyType = partyType.toPresentation(),
)

fun PartyType.toPresentation(): PartyTypeModel = PartyTypeModel(
    type = type,
)