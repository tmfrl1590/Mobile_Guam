package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY500
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.RED
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.util.convertIsoToCustomDateFormat

@Composable
fun RecruitmentInfoSection(
    recruitingCount: String,
    applicationCount: Int,
    createDate: String,
){
    Column(
        modifier = Modifier
            .padding(horizontal = MEDIUM_PADDING_SIZE),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        RecruitmentInfoItem(
            title = "모집중",
            content = recruitingCount,
            textColor = RED,
        )
        RecruitmentInfoItem(
            title = "지원자",
            content = applicationCount.toString(),
            textColor = PRIMARY,
        )
        RecruitmentInfoItem(
            title = "모집일",
            content = convertIsoToCustomDateFormat(createDate),
            textColor = BLACK,
        )
    }
}

@Composable
private fun RecruitmentInfoItem(
    title: String,
    content: String,
    textColor: Color,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomText(
            text = title,
            fontSize = B1,
            color = GRAY500,
        )
        WidthSpacer(widthDp = 6.dp)
        CustomText(
            text = content,
            fontSize = B1,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
        )
    }
}