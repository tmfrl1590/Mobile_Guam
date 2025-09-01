package com.party.guham2.core.data

import platform.posix.printf

internal actual fun platformLogLine(tag: String, line: String) {
    printf("%s %s\n", tag, line)
}