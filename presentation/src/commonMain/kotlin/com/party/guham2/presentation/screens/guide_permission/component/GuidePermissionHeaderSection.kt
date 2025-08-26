package com.party.guham2.presentation.screens.guide_permission.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.party.guham2.design.T2
import com.party.guham2.design.component.header.CenterTopAppBar

@Composable
fun GuidePermissionHeaderSection(){
    CenterTopAppBar(
        title = {
            Text(
                text = "앱 접근 권한 안내",
                fontWeight = FontWeight.Bold,
                fontSize = T2
            )
        }
    )
}