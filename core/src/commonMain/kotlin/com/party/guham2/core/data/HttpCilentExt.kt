package com.party.guham2.core.data

import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlinx.io.IOException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T, reified E> safeCall(
    execute: () -> HttpResponse
): Result<T, DataErrorRemote<E>> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        e.printStackTrace()
        return Result.Error(DataErrorRemote.RequestTimeout())
    } catch (e: IOException) {
        e.printStackTrace()
        return Result.Error(DataErrorRemote.NoInternet())
    } catch (e: Exception) {
        e.printStackTrace()
        coroutineContext.ensureActive()
        return Result.Error(DataErrorRemote.Unknown())
    }

    return responseToResult(response)
}

suspend inline fun <reified T, reified E> responseToResult(
    response: HttpResponse
): Result<T, DataErrorRemote<E>> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataErrorRemote.Serialization())
            }
        }
        400 -> Result.Error(DataErrorRemote.BadRequest(response.bodyOrNull()))
        401 -> Result.Error(DataErrorRemote.Unauthorized(response.bodyOrNull()))
        408 -> Result.Error(DataErrorRemote.RequestTimeout(response.bodyOrNull()))
        409 -> Result.Error(DataErrorRemote.Conflict(response.bodyOrNull()))
        429 -> Result.Error(DataErrorRemote.TooManyRequests(response.bodyOrNull()))
        in 500..599 -> Result.Error(DataErrorRemote.ServerError(response.bodyOrNull()))
        else -> Result.Error(DataErrorRemote.Unknown(response.bodyOrNull()))
    }
}

/**
 * HttpResponse에서 body를 읽어오되, 실패 시 null을 반환하는 확장 함수.
 */
suspend inline fun <reified T> HttpResponse.bodyOrNull(): T? {
    return try {
        body<T>()
    } catch (e: Exception) {
        null
    }
}
