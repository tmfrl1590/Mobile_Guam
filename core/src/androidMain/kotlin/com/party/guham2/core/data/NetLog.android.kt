package com.party.guham2.core.data

import android.util.Log

internal actual fun platformLogLine(tag: String, line: String) {
    Log.d(tag, line)    // 라인마다 별도 출력 → Logcat에서 실제로 줄바꿈됨
}