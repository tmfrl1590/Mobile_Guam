package com.party.guham2.presentation.model.party

import com.party.guham2.model.party.PartyRecruitment
import com.party.guham2.model.party.Position1

data class PartyRecruitmentModel(
    val id: Int = 0,
    val position: PositionModel1 = PositionModel1(),
    val content: String = "",
    val status: String = "",
    val recruitingCount: Int = 0,
    val recruitedCount: Int = 0,
    val applicationCount: Int = 0,
    val createdAt: String = "",
    val isOptionsRevealed: Boolean = false,
)

data class PositionModel1(
    val main: String = "",
    val sub: String = "",
)

fun PartyRecruitment.toPresentation(): PartyRecruitmentModel {
    return PartyRecruitmentModel(
        id = id,
        position = position.toPresentation(),
        content = content,
        status = status,
        recruitingCount = recruitingCount,
        recruitedCount = recruitedCount,
        applicationCount = applicationCount,
        createdAt = createdAt,
        isOptionsRevealed = isOptionsRevealed,
    )
}

fun Position1.toPresentation(): PositionModel1 {
    return PositionModel1(
        main = this.main,
        sub = this.sub,
    )
}