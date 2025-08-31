package com.party.guham2.model.user.join

import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpRequest(
    val nickname: String,
    val birth: String,
    val gender: String,
)
