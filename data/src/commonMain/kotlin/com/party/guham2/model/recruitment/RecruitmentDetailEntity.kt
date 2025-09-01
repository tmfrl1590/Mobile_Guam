package com.party.guham2.model.recruitment

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDetailEntity(
    val party: RecruitmentDetailPartyEntity,
    val position: RecruitmentDetailPositionEntity,
    val content: String,
    val recruitingCount: Int,
    val recruitedCount: Int,
    val applicationCount: Int,
    val createdAt: String,
): DataMapper<RecruitmentDetail>{
    override fun toDomain(): RecruitmentDetail {
        return RecruitmentDetail(
            party = party.toDomain(),
            position = position.toDomain(),
            content = content,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            applicationCount = applicationCount,
            createdAt = createdAt
        )
    }
}

@Serializable
data class RecruitmentDetailPartyEntity(
    val title: String,
    val image: String?,
    val status: String,
    val partyType: RecruitmentDetailPartyTypeEntity,
): DataMapper<RecruitmentDetailParty>{
    override fun toDomain(): RecruitmentDetailParty {
        return RecruitmentDetailParty(
            title = title,
            image = DataConstants.convertToImageUrl(image),
            status = status,
            partyType = partyType.toDomain()
        )
    }
}

@Serializable
data class RecruitmentDetailPartyTypeEntity(
    val type: String,
): DataMapper<RecruitmentDetailPartyType>{
    override fun toDomain(): RecruitmentDetailPartyType {
        return RecruitmentDetailPartyType(
            type = type
        )
    }
}

@Serializable
data class RecruitmentDetailPositionEntity(
    val id: Int,
    val main: String,
    val sub: String,
): DataMapper<RecruitmentDetailPosition>{
    override fun toDomain(): RecruitmentDetailPosition {
        return RecruitmentDetailPosition(
            id = id,
            main = main,
            sub = sub
        )
    }
}