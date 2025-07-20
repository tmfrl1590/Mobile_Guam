package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.core.domain.map
import com.party.guham2.model.banner.Banner
import com.party.guham2.remote.BannerRemoteSource

class BannerRepositoryImpl(
    private val bannerRemoteSource: BannerRemoteSource
): BannerRepository {
    override suspend fun getBannerList(): Result<Banner, DataError> {
        return bannerRemoteSource.getBannerList().map { it.toDomain() }
    }
}