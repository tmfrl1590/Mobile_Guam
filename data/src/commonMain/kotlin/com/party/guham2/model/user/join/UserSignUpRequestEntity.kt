package com.party.guham2.model.user.join

import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpRequestEntity(
    val nickname: String,
    val birth: String,
    val gender: String,
)

fun UserSignUpRequest.toEntity(): UserSignUpRequestEntity {
    return UserSignUpRequestEntity(
        nickname = nickname,
        birth = birth,
        gender = gender
    )
}
