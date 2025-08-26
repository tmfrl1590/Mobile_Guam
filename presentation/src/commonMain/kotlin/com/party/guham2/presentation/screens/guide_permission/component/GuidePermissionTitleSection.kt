package com.party.guham2.presentation.screens.guide_permission.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.party.guham2.design.T2
import com.party.guham2.design.component.text.CustomText

@Composable
fun GuidePermissionTitleSection(){
    CustomText(
        text = "다양한 서비스 제공을 위해\n권한을 허용해 주세요.",
        fontSize = T2,
        fontWeight = FontWeight.Bold
    )
}