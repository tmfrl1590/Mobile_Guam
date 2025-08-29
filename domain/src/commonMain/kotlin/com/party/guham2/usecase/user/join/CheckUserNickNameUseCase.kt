package com.party.guham2.usecase.user.join

import com.party.guham2.repository.UserRepository

class CheckUserNickNameUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(signupAccessToken: String, nickname: String) = userRepository.checkNickName(signupAccessToken = signupAccessToken, nickname = nickname)
}