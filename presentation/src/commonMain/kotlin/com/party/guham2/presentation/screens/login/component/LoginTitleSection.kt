package com.party.guham2.presentation.screens.login.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.party.guham2.design.T2
import com.party.guham2.design.component.header.CenterTopAppBar
import com.party.guham2.design.component.text.CustomText

@Composable
fun LoginTitleSection(){
    CenterTopAppBar(
        title = {
            CustomText(
                text = "로그인",
                fontSize = T2,
                fontWeight = FontWeight.Bold
            )
        }
    )
}