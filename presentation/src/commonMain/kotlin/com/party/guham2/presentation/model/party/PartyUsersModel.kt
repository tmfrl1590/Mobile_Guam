package com.party.guham2.presentation.model.party

import com.party.guham2.model.party.PartyAdmin
import com.party.guham2.model.party.PartyMember
import com.party.guham2.model.party.PartyUser
import com.party.guham2.model.party.PartyUsers
import com.party.guham2.model.party.Position
import com.party.guham2.model.party.User
import com.party.guham2.model.party.UserCareer

data class PartyUsersModel(
    val partyAdmin: List<PartyAdminModel> = emptyList(),
    val partyUser: List<PartyUserModel> = emptyList()
)

data class PartyAdminModel(
    val id: Int,
    val authority: String,
    val position: PositionModel,
    val user: UserModel
)

data class PartyUserModel(
    val id: Int,
    val authority: String,
    val position: PositionModel,
    val user: UserModel
)

data class PositionModel(
    val id: Int,
    val main: String,
    val sub: String,
)

data class UserModel(
    val nickname: String,
    val image: String?,
    val userCareers: List<UserCareerModel> = emptyList()
)

data class UserCareerModel(
    val positionId: Int,
    val years: Int,
)


fun PartyUsers.toPresentation(): PartyUsersModel {
    return PartyUsersModel(
        partyAdmin = partyAdmin.map { it.toPresentation() },
        partyUser = partyUser.map { it.toPresentation() }
    )
}

fun PartyAdmin.toPresentation(): PartyAdminModel {
    return PartyAdminModel(
        id = id,
        authority = authority,
        position = position.toPresentation(),
        user = user.toPresentation()
    )
}

fun PartyUser.toPresentation(): PartyUserModel {
    return PartyUserModel(
        id = id,
        authority = authority,
        position = position.toPresentation(),
        user = user.toPresentation()
    )
}

fun Position.toPresentation(): PositionModel {
    return PositionModel(
        id = id,
        main = main,
        sub = sub
    )
}

fun User.toPresentation(): UserModel {
    return UserModel(
        nickname = nickname,
        image = image,
        userCareers = userCareers.map { it.toPresentation() }
    )
}

fun UserCareer.toPresentation(): UserCareerModel {
    return UserCareerModel(
        positionId = positionId,
        years = years
    )
}

data class PartyMemberModel(
    val authority: String,
    val main: String,
    val sub: String,
    val nickName: String,
    val image: String,
    val userId: Int,
)

fun PartyAdminModel.toPartyMember(): PartyMemberModel {
    return PartyMemberModel(
        authority = authority,
        main = position.main,
        sub = position.sub,
        nickName = user.nickname,
        image = user.image ?: "",
        userId = id
    )
}

fun PartyUserModel.toPartyMember(): PartyMemberModel {
    return PartyMemberModel(
        authority = authority,
        main = position.main,
        sub = position.sub,
        nickName = user.nickname,
        image = user.image ?: "",
        userId = id
    )
}