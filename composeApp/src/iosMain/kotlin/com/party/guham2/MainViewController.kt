package com.party.guham2

import androidx.compose.ui.window.ComposeUIViewController
import com.party.guham2.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }