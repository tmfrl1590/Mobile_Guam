package com.party.guham2.model.party

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PartyRecruitmentEntity(
    val id: Int,
    val position: PositionEntity1,
    val content: String,
    val status: String,
    val recruitingCount: Int,
    val recruitedCount: Int,
    val applicationCount: Int,
    val createdAt: String,
): DataMapper<PartyRecruitment>{
    override fun toDomain(): PartyRecruitment {
        return PartyRecruitment(
            id = id,
            position = position.toDomain(),
            content = content,
            status = status,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            applicationCount = applicationCount,
            createdAt = createdAt
        )
    }
}

@Serializable
data class PositionEntity1(
    val main: String,
    val sub: String,
): DataMapper<Position1>{
    override fun toDomain(): Position1 {
        return Position1(
            main = main,
            sub = sub
        )
    }
}
