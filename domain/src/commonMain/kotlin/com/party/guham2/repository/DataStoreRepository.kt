package com.party.guham2.repository

interface DataStoreRepository {
    suspend fun getAccessToken(): String?

}