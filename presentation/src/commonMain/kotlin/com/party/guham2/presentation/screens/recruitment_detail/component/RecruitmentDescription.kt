package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.GRAY600
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer

@Composable
fun RecruitmentDescription(
    content: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING_SIZE),
    ) {
        RecruitmentTitle(
            title = "모집 공고",
        )
        HeightSpacer(heightDp = 20.dp)
        RecruitmentDescriptionSection(
            content = content
        )
    }
}

@Composable
fun RecruitmentDescriptionSection(
    content: String,
) {
    CustomText(
        text = content,
        fontSize = B1,
        color = GRAY600,
    )
}