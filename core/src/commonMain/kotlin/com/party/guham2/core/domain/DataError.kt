package com.party.guham2.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERIALIZATION,
        UNAUTHORIZED,      // 401
        FORBIDDEN,         // 403
        SERVER, // 500
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}