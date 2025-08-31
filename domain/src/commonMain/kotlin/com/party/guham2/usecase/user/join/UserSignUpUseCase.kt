package com.party.guham2.usecase.user.join

import com.party.guham2.model.user.join.UserSignUpRequest
import com.party.guham2.repository.UserRepository

class UserSignUpUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(signupAccessToken: String, userSignUpRequest: UserSignUpRequest) = userRepository.userSignUp(signupAccessToken = signupAccessToken, userSignUpRequest = userSignUpRequest)
}