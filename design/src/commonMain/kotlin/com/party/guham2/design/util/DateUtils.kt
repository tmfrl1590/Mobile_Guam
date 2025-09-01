package com.party.guham2.design.util

import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
fun convertIsoToCustomDateFormat(isoString: String?): String {
    if (isoString.isNullOrBlank()) return ""

    val instant = runCatching { Instant.parse(isoString) }.getOrNull() ?: return ""

    val localDateTime: LocalDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val year = localDateTime.year.toString().padStart(4, '0')
    val month = localDateTime.month.number.toString().padStart(2, '0')
    val day = localDateTime.day.toString().padStart(2, '0')

    return "$year.$month.$day"
}