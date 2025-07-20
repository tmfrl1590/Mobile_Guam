package com.party.guham2

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.party.guham2.presentation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}