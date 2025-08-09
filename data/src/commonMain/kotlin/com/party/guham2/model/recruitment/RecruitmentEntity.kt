package com.party.guham2.model.recruitment

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentEntity(
    val partyRecruitments: List<RecruitmentItemEntity>,
    val total: Int,
): DataMapper<Recruitment> {
    override fun toDomain(): Recruitment {
        return Recruitment(
            partyRecruitments = partyRecruitments.map { item -> item.toDomain() },
            total = total
        )
    }
}

@Serializable
data class RecruitmentItemEntity(
    val id: Int,
    val recruitingCount: Int,
    val recruitedCount: Int,
    val content: String,
    val createdAt: String,
    val party: RecruitmentPartyEntity,
    val position: RecruitmentPositionEntity,
): DataMapper<RecruitmentItem> {
    override fun toDomain(): RecruitmentItem {
        return RecruitmentItem(
            id = id,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            content = content,
            createdAt = createdAt,
            party = party.toDomain(),
            position = position.toDomain()
        )
    }
}

@Serializable
data class RecruitmentPartyEntity(
    val id: Int,
    val title: String,
    val image: String?,
    val partyType: RecruitmentPartyTypeEntity,
): DataMapper<RecruitmentParty> {
    override fun toDomain(): RecruitmentParty {
        return RecruitmentParty(
            id = id,
            title = title,
            image = DataConstants.convertToImageUrl(image),
            partyType = partyType.toDomain()
        )
    }
}

@Serializable
data class RecruitmentPartyTypeEntity(
    val id: Int,
    val type: String,
): DataMapper<RecruitmentPartyType> {
    override fun toDomain(): RecruitmentPartyType {
        return RecruitmentPartyType(
            id = id,
            type = type
        )
    }
}

@Serializable
data class RecruitmentPositionEntity(
    val id: Int,
    val main: String,
    val sub: String,
): DataMapper<RecruitmentPosition> {
    override fun toDomain(): RecruitmentPosition {
        return RecruitmentPosition(
            id = id,
            main = main,
            sub = sub
        )
    }
}
