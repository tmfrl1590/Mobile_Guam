package com.party.guham2.model.banner

import com.party.DataConstants
import com.party.guham2.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class BannerEntity(
    val total: Int,
    val banner: List<BannerItemEntity>,
): DataMapper<Banner>{
    override fun toDomain(): Banner {
        return Banner(
            total = total,
            banner = banner.map { item -> item.toDomain() }
        )
    }
}

@Serializable
data class BannerItemEntity(
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val id: Int,
    val platform: String,
    val title: String,
    val image: String,
    val link: String,
): DataMapper<BannerItem>{
    override fun toDomain(): BannerItem {
        return BannerItem(
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt,
            id = id,
            platform = platform,
            title = title,
            image = DataConstants.convertToImageUrl(image),
            link = link
        )
    }
}
