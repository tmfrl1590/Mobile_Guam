package com.party.guham2.model.party

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class PartyUsersEntity(
    val partyAdmin: List<PartyAdminEntity>,
    val partyUser: List<PartyUserEntity> = emptyList()
): DataMapper<PartyUsers>{
    override fun toDomain(): PartyUsers {
        return PartyUsers(
            partyAdmin = partyAdmin.map { it.toDomain() },
            partyUser = partyUser.map { it.toDomain() }
        )
    }
}

@Serializable
data class PartyAdminEntity(
    val id: Int,
    val authority: String,
    val position: PositionEntity,
    val user: UserEntity
): DataMapper<PartyAdmin>{
    override fun toDomain(): PartyAdmin {
        return PartyAdmin(
            id = id,
            authority = authority,
            position = position.toDomain(),
            user = user.toDomain()
        )
    }
}

@Serializable
data class PartyUserEntity(
    val id: Int,
    val authority: String,
    val position: PositionEntity,
    val user: UserEntity
): DataMapper<PartyUser>{
    override fun toDomain(): PartyUser {
        return PartyUser(
            id = id,
            authority = authority,
            position = position.toDomain(),
            user = user.toDomain()
        )
    }
}

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
            sub = sub
        )
    }
}

@Serializable
data class UserEntity(
    val nickname: String,
    val image: String?,
    val userCareers: List<UserCareerEntity> = emptyList()
): DataMapper<User>{
    override fun toDomain(): User {
        return User(
            nickname = nickname,
            image = DataConstants.convertToImageUrl(image),
            userCareers = userCareers.map { it.toDomain() }
        )
    }
}

@Serializable
data class UserCareerEntity(
    val positionId: Int,
    val years: Int,
): DataMapper<UserCareer>{
    override fun toDomain(): UserCareer {
        return UserCareer(
            positionId = positionId,
            years = years
        )
    }
}