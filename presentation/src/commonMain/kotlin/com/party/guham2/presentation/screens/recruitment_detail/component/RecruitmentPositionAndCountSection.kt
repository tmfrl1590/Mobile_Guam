package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY500
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun RecruitmentPositionAndCountSection(
    position: String,
    recruitingCount: String,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING_SIZE),
    ) {
        RecruitmentTitle(
            title = "모집 부분",
        )
        HeightSpacer(heightDp = 20.dp)
        RecruitmentPositionAndCount(
            position = position,
            recruitingCount = recruitingCount,
        )
    }
}

@Composable
private fun RecruitmentPositionAndCount(
    position: String,
    recruitingCount: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        RecruitmentPositionAndCountItem(
            title = "포지션",
            content = position,
        )
        HeightSpacer(heightDp = 12.dp)
        RecruitmentPositionAndCountItem(
            title = "인원",
            content = recruitingCount,
        )
    }
}


@Composable
private fun RecruitmentPositionAndCountItem(
    title: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomText(
            modifier = Modifier
                .width(48.dp),
            text = title,
            fontSize = B1,
            color = GRAY500,
        )
        WidthSpacer(widthDp = 6.dp)
        CustomText(
            text = content,
            fontSize = B1,
            color = BLACK,
            fontWeight = FontWeight.SemiBold
        )
    }
}