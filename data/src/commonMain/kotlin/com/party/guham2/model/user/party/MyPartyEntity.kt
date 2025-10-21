package com.party.guham2.model.user.party

import com.party.DataConstants.convertToImageUrl
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class MyPartyEntity(
    val total: Int,
    val partyUsers: List<PartyUserEntity>
): DataMapper<MyParty>{
    override fun toDomain(): MyParty {
        return MyParty(
            total = total,
            partyUsers = partyUsers.map { it.toDomain() }
        )
    }
}

@Serializable
data class PartyUserEntity(
    val id: Int,
    val createdAt: String,
    val position: PositionEntity,
    val party: PartyEntity
): DataMapper<PartyUser>{
    override fun toDomain(): PartyUser {
        return PartyUser(
            id = id,
            createdAt = createdAt,
            position = position.toDomain(),
            party = party.toDomain()
        )
    }
}

@Serializable
data class PositionEntity(
    val main: String,
    val sub: String
): DataMapper<Position>{
    override fun toDomain(): Position {
        return Position(
            main = main,
            sub = sub
        )
    }
}

@Serializable
data class PartyEntity(
    val id: Int,
    val title: String,
    val image: String?,
    val status: String,
    val partyType: PartyTypeEntity
): DataMapper<Party>{
    override fun toDomain(): Party {
        return Party(
            id = id,
            title = title,
            image = convertToImageUrl(imageUrl = image),
            status = status,
            partyType = partyType.toDomain()
        )
    }
}

@Serializable
data class PartyTypeEntity(
    val type: String
): DataMapper<PartyType>{
    override fun toDomain(): PartyType {
        return PartyType(
            type = type
        )
    }
}