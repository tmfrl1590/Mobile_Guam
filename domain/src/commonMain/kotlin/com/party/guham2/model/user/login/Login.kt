package com.party.guham2.model.user.login

sealed class Login

data class LoginSuccess(
    val accessToken: String,
    val refreshToken: String,
): Login()

data class LoginFailure(
    val message: String,
    val signupAccessToken: String,
    var userEmail: String? = null,
): Login()
