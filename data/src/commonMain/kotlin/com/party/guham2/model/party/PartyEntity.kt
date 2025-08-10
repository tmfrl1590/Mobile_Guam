package com.party.guham2.model.party

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PartyEntity(
    val parties: List<PartyItemEntity>,
    val total: Int,
): DataMapper<Party>{
    override fun toDomain(): Party {
        return Party(
            parties = parties.map { it.toDomain() },
            total = total,
        )
    }
}

@Serializable
data class PartyItemEntity(
    val id: Int,
    val partyType: PartyTypeItemEntity,
    val title: String,
    val content: String,
    val image: String? = null,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val recruitmentCount: Int,
): DataMapper<PartyItem>{
    override fun toDomain(): PartyItem {
        return PartyItem(
            id = id,
            partyType = partyType.toDomain(),
            title = title,
            content = content,
            image = DataConstants.convertToImageUrl(image),
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt,
            recruitmentCount = recruitmentCount,
        )
    }
}

@Serializable
data class PartyTypeItemEntity(
    val id: Int,
    val type: String,
): DataMapper<PartyTypeItem>{
    override fun toDomain(): PartyTypeItem {
        return PartyTypeItem(
            id = id,
            type = type
        )
    }
}