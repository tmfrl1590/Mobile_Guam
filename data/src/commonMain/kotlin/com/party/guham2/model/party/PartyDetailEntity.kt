package com.party.guham2.model.party

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PartyDetailEntity(
    val id: Int,
    val partyType: PartyTypeEntity,
    val title: String,
    val content: String,
    val image: String ?= null,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
): DataMapper<PartyDetail>{
    override fun toDomain(): PartyDetail {
        return PartyDetail(
            id = id,
            partyType = partyType.toDomain(),
            title = title,
            content = content,
            image = DataConstants.convertToImageUrl(image),
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}

@Serializable
data class PartyTypeEntity(
    val id: Int,
    val type: String
): DataMapper<PartyType>{
    override fun toDomain(): PartyType {
        return PartyType(
            id = id,
            type = type
        )
    }
}