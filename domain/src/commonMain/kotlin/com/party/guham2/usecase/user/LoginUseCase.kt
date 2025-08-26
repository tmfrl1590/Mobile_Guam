package com.party.guham2.usecase.user

import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(accessTokenRequest: AccessTokenRequest) = userRepository.loginGoogle(accessTokenRequest = accessTokenRequest)
}