package com.party.guham2.model.user.login

import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class LoginSuccessEntity(
    val accessToken: String,
    val refreshToken: String,
): DataMapper<LoginSuccess>{
    override fun toDomain(): LoginSuccess {
        return LoginSuccess(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}


@Serializable
data class LoginFailureEntity(
    val message: String,
    val signupAccessToken: String,
): DataMapper<LoginFailure>{
    override fun toDomain(): LoginFailure {
        return LoginFailure(
            message = message,
            signupAccessToken = signupAccessToken,
        )
    }
}