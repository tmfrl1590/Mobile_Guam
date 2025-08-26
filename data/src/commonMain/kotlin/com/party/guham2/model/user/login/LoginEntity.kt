package com.party.guham2.model.user.login

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
sealed class LoginEntity : DataMapper<Login> {
    override fun toDomain(): Login = when (this) {
        is LoginSuccessEntity -> LoginSuccess(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
        is LoginFailureEntity -> LoginFailure(
            message = message,
            signupAccessToken = signupAccessToken
        )
    }
}

@Serializable
data class LoginSuccessEntity(
    val accessToken: String,
    val refreshToken: String,
): LoginEntity()


@Serializable
data class LoginFailureEntity(
    val message: String,
    val signupAccessToken: String,
): LoginEntity()