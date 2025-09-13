package com.party.guham2.model.user

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PartyAuthorityEntity(
    val id: Int,
    val authority: String,
    val position: PartyAuthorityPositionEntity
): DataMapper<PartyAuthority>{
    override fun toDomain(): PartyAuthority {
        return PartyAuthority(
            id = id,
            authority = authority,
            position = position.toDomain()
        )
    }
}

@Serializable
data class PartyAuthorityPositionEntity(
    val id: Int,
    val main: String,
    val sub: String,
): DataMapper<PartyAuthorityPosition>{
    override fun toDomain(): PartyAuthorityPosition {
        return PartyAuthorityPosition(
            id = id,
            main = main,
            sub = sub
        )
    }
}