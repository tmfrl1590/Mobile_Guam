package com.party.guham2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform