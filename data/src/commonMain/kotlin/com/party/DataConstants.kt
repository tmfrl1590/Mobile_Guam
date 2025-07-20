package com.party

internal object DataConstants {

    private const val IMAGE_URL = "https://partyguham-test.s3.ap-northeast-2.amazonaws.com/"

    fun convertToImageUrl(imageUrl: String?): String {
        return IMAGE_URL + imageUrl
    }
}