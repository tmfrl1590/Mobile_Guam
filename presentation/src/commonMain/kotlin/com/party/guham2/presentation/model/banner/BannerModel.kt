package com.party.guham2.presentation.model.banner

import com.party.guham2.model.banner.Banner
import com.party.guham2.model.banner.BannerItem

data class BannerModel(
    val bannerList: List<BannerItemModel> = emptyList()
)

data class BannerItemModel(
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val id: Int,
    val platform: String,
    val title: String,
    val image: String,
    val link: String,
)

fun Banner.toPresentation(): BannerModel = BannerModel(
    bannerList = banner.map { it.toPresentation() }
)

fun BannerItem.toPresentation(): BannerItemModel = BannerItemModel(
    status = status,
    createdAt = createdAt,
    updatedAt = updatedAt,
    id = id,
    platform = platform,
    title = title,
    image = image,
    link = link
)