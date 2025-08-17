package com.party.guham2.model.user

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PositionEntity(
    val id: Int,
    val main: String,
    val sub: String,
): DataMapper<Position>{
    override fun toDomain(): Position {
        return Position(
            id = id,
            main = main,
            sub = sub,
        )
    }
}
