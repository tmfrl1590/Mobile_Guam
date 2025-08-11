package com.party.guham2

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.party.guham2.design.DesignResources
import com.party.guham2.presentation.AppNavHost
import guamapplication.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val customFont = FontFamily(Font(DesignResources.Font.suit_regular))
    val customTypography = Typography(
        bodyMedium = TextStyle(
            fontFamily = customFont,
        )
    )

    MaterialTheme(
        typography = customTypography
    ) {
        AppNavHost()
    }
}