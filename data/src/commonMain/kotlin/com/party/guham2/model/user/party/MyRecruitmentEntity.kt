package com.party.guham2.model.user.party

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class MyRecruitmentEntity(
    val total: Int,
    val partyApplications: List<PartyApplicationEntity>
): DataMapper<MyRecruitment>{
    override fun toDomain(): MyRecruitment {
        return MyRecruitment(
            total = total,
            partyApplications = partyApplications.map { it.toDomain() }
        )
    }
}

@Serializable
data class PartyApplicationEntity(
    val id: Int,
    val message: String,
    val status: String,
    val createdAt: String,
    val partyRecruitment: PartyRecruitmentEntity
): DataMapper<PartyApplication>{
    override fun toDomain(): PartyApplication {
        return PartyApplication(
            id = id,
            message = message,
            status = status,
            createdAt = createdAt,
            partyRecruitment = partyRecruitment.toDomain()
        )
    }
}

@Serializable
data class PartyRecruitmentEntity(
    val id: Int,
    val status: String,
    val position: PositionEntity1,
    val party: PartyEntity1
): DataMapper<PartyRecruitment> {
    override fun toDomain(): PartyRecruitment {
        return PartyRecruitment(
            id = id,
            status = status,
            position = position.toDomain(),
            party = party.toDomain()
        )
    }
}

@Serializable
data class PositionEntity1(
    val main: String,
    val sub: String
): DataMapper<Position1>{
    override fun toDomain(): Position1 {
        return Position1(
            main = main,
            sub = sub
        )
    }
}

@Serializable
data class PartyEntity1(
    val id: Int,
    val title: String,
    val image: String?,
    val partyType: PartyTypeEntity1
): DataMapper<Party1>{
    override fun toDomain(): Party1 {
        return Party1(
            id = id,
            title = title,
            image = image,
            partyType = partyType.toDomain()
        )
    }
}

@Serializable
data class PartyTypeEntity1(
    val type: String
): DataMapper<PartyType1>{
    override fun toDomain(): PartyType1 {
        return PartyType1(
            type = type
        )
    }
}