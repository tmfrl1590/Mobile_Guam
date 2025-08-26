package com.party.guham2.local

interface DataStoreSource {

    suspend fun saveAccessToken(accessToken: String)
}