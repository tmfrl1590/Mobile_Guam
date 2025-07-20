package com.party.guham2.usecase.banner

import com.party.guham2.repository.BannerRepository

class GetBannerListUseCase(
    private val bannerRepository: BannerRepository
) {
    suspend operator fun invoke() = bannerRepository.getBannerList()
}