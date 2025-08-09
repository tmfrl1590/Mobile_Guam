package com.party.guham2.remote

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.banner.BannerEntity

interface BannerDataSource {
    suspend fun getBannerList(): Result<BannerEntity, DataError>
}