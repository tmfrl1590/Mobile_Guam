package com.party.guham2.core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    private val prettyJsonFormatter = Json {
        prettyPrint = true
        prettyPrintIndent = "  "
        ignoreUnknownKeys = true
    }

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        when {
                            "REQUEST:" in message -> {
                                println("🚀 HTTP REQUEST: ${message.substringAfter("REQUEST:")}")
                            }

                            "METHOD:" in message -> {
                                println("📋 METHOD: ${message.substringAfter("METHOD:")}")
                            }

                            "BODY START" in message -> {
                                val rawJson = message
                                    .substringAfter("BODY START")
                                    .substringBefore("BODY END")
                                    .trim()

                                try {
                                    val pretty = prettyJsonFormatter.encodeToString(
                                        Json.parseToJsonElement(rawJson)
                                    )
                                    println("📦 REQUEST/RESPONSE BODY:\n$pretty")
                                } catch (e: Exception) {
                                    println("📦 BODY (JSON 파싱 실패):\n$rawJson")
                                }
                            }
                            "RESPONSE:" in message -> {
                                println("📨 HTTP RESPONSE: ${message.substringAfter("RESPONSE:")}")
                            }

                            "HEADERS" in message -> {
                                println("🔧 HEADERS: ${message.substringAfter("HEADERS")}")
                            }

                            else -> {
                                println("🔸 $message")
                            }
                        }
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}