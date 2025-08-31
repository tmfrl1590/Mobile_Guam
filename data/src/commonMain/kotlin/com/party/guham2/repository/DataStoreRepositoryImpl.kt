package com.party.guham2.repository

import com.party.guham2.local.DataStoreSource

class DataStoreRepositoryImpl(
    private val dataStoreSource: DataStoreSource,
): DataStoreRepository {
    override suspend fun getAccessToken(): String? {
        return dataStoreSource.getAccessToken()
    }
}