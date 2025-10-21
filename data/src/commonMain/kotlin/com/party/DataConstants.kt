package com.party

internal object DataConstants {

    private const val IMAGE_URL = "https://partyguham-test.s3.ap-northeast-2.amazonaws.com/"

    private const val DEFAULT_IMAGE = "https://partyguham.s3.ap-northeast-2.amazonaws.com/assets/images/default/default-party-light200.jpg"

    fun convertToImageUrl(imageUrl: String?): String {
        return if(imageUrl != null) IMAGE_URL + imageUrl else DEFAULT_IMAGE
    }
}