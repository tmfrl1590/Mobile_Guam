package com.party.guham2.core.data

import io.ktor.client.HttpClient
import io.ktor.client.call.save
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

object HttpClientFactory {
    fun create(engine: HttpClientEngine): HttpClient =
        HttpClient(engine) {
            install(ContentNegotiation) { json(prettyJson) }

            // 응답 전체 바디를 '복제'해서 안전하게 읽고 줄바꿈 출력
            install(ResponseObserver) {
                onResponse { resp ->
                    val saved = runCatching { resp.call.save() }.getOrNull()
                    val body = runCatching { saved?.response?.bodyAsText() }.getOrNull()

                    platformLogLine(TAG, "RESPONSE ${resp.status.value} ${resp.request.url}")
                    if (body != null) {
                        platformLogLine(TAG, "BODY ↓")
                        prettyPrintJsonOrRaw(body)
                    }
                }
            }

            install(Logging) {
                level = LogLevel.HEADERS   // 헤더/메타만 기본 로깅
                logger = object : Logger {
                    override fun log(message: String) = platformLogLine(TAG, message)
                }
            }

            defaultRequest { contentType(ContentType.Application.Json) }
        }
}
