package com.party.guham2.presentation.model.user

import com.party.guham2.model.user.PartyAuthority
import com.party.guham2.model.user.PartyAuthorityPosition

data class PartyAuthorityModel(
    val id: Int = 0,
    val authority: String = "",
    val position: PartyAuthorityPositionModel = PartyAuthorityPositionModel()
)

data class PartyAuthorityPositionModel(
    val id: Int = 0,
    val main: String = "",
    val sub: String = "",
)

fun PartyAuthority.toPresentation() = PartyAuthorityModel(
    id = id,
    authority = authority,
    position = position.toPresentation()
)

fun PartyAuthorityPosition.toPresentation() = PartyAuthorityPositionModel(
    id = id,
    main = main,
    sub = sub,
)