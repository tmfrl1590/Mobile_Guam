package com.party.guham2.presentation.screens.guide_permission.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.B3
import com.party.guham2.design.GRAY500
import com.party.guham2.design.component.text.CustomText

@Composable
fun GuidePermissionDescriptionSection(){
    CustomText(
        text = "해당 기능 사용을 원하실 때만 동의를 받고 있으며, 비동의 시에도 해당 기능 외 서비스 이용이 가능해요",
        color = GRAY500,
        fontSize = B3
    )
}