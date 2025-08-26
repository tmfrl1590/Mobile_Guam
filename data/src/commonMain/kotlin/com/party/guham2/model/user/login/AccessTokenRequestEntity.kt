package com.party.guham2.model.user.login

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenRequestEntity(
    val idToken: String,
)

fun AccessTokenRequest.toEntity(): AccessTokenRequestEntity {
    return AccessTokenRequestEntity(
        idToken = this.idToken
    )
}