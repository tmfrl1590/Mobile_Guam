package com.party.guham2.presentation.model.user

import com.party.guham2.model.user.Position

data class PositionModel(
    val positionList: List<PositionItemModel>
)

data class PositionItemModel(
    val id: Int,
    val main: String,
    val sub: String,
)

fun Position.toPresentation(): PositionItemModel = PositionItemModel(
    id = id,
    main = main,
    sub = sub,
)