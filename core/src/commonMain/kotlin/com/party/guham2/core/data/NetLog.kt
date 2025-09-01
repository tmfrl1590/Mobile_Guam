package com.party.guham2.core.data

import kotlinx.serialization.json.Json

internal expect fun platformLogLine(tag: String, line: String)
const val TAG = "network-api"

val prettyJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    prettyPrintIndent = "  "
}

private fun printChunked(line: String, chunk: Int = 3500) {
    var i = 0
    while (i < line.length) {
        val end = (i + chunk).coerceAtMost(line.length)
        platformLogLine(TAG, line.substring(i, end))
        i = end
    }
}

fun prettyPrintJsonOrRaw(text: String) {
    val trimmed = text.trim()
    val jsonStart = trimmed.indexOfFirst { it == '{' || it == '[' }
    if (jsonStart >= 0) {
        val prefix = trimmed.substring(0, jsonStart).trim()
        val json = trimmed.substring(jsonStart)
        val pretty = runCatching {
            val el = Json.parseToJsonElement(json)
            prettyJson.encodeToString(el)
        }.getOrNull()
        if (pretty != null) {
            if (prefix.isNotEmpty()) printChunked(prefix)
            pretty.lineSequence().forEach { printChunked(it) }
            return
        }
    }
    // JSON 아니면 그대로 줄단위
    trimmed.lineSequence().forEach { printChunked(it) }
}




