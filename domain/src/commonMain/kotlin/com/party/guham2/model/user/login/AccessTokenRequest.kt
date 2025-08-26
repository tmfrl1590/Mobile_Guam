package com.party.guham2.model.user.login

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenRequest(
    val idToken: String,
)
