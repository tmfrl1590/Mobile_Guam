package com.party.guham2.model.user.login


data class LoginSuccess(
    val accessToken: String,
    val refreshToken: String,
)

data class LoginFailure(
    val message: String = "",
    val signupAccessToken: String = "",
    var userEmail: String? = null,
)
