package com.party.guham2.usecase.local

import com.party.guham2.repository.DataStoreRepository

class GetAccessTokenUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): String? = dataStoreRepository.getAccessToken()
}