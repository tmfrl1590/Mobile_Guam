package com.party.guham2.remote.network.banner

import com.party.guham2.core.data.safeCall
import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.banner.BannerEntity
import com.party.guham2.remote.BannerRemoteSource
import com.party.guham2.remote.RemoteConstants.serverUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class BannerRemoteSourceImpl(
    private val httpClient: HttpClient
): BannerRemoteSource {
    override suspend fun getBannerList(): Result<BannerEntity, DataError> {
        return safeCall<BannerEntity> {
            httpClient.get(
                urlString = serverUrl("api/banner/app")
            )
        }
    }
}