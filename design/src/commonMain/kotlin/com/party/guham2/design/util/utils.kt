package com.party.guham2.design.util

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun calculateLetterSpacing(fontSize: TextUnit, percentage: Float = -1f): TextUnit {
    return (fontSize.value * percentage / 100).sp
}