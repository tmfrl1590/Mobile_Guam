package com.party.guham2.usecase.user

import com.party.guham2.repository.UserRepository

class CheckUserApplicationStatusUseCase(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(partyId: Int, partyRecruitmentId: Int) = userRepository.checkUserApplicationStatus(partyId = partyId, partyRecruitmentId = partyRecruitmentId)
}