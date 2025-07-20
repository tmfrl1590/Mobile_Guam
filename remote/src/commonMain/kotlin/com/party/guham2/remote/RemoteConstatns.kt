package com.party.guham2.remote

internal object RemoteConstants {

    private const val SERVER_URL = "https://partyguham.com/dev/"

    fun serverUrl(urlString: String): String {
        return "$SERVER_URL$urlString"
    }


}