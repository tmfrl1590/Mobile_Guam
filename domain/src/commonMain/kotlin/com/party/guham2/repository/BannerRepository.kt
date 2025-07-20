package com.party.guham2.repository

import com.party.guham2.core.domain.DataError
import com.party.guham2.core.domain.Result
import com.party.guham2.model.banner.Banner

interface BannerRepository {
    // 배너 리스트 조회
    suspend fun getBannerList(): Result<Banner, DataError>
}