package com.party.guham2.usecase.user

import com.party.guham2.repository.UserRepository

class GetPositionListUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(main: String) = userRepository.getPositionList(main = main)
}