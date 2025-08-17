package com.party.guham2.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.GRAY100
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.MainAndSubPosition
import com.party.guham2.design.component.util.RecruitmentCountingSection
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun RecruitmentCard2(
    id: Int,
    partyId: Int,
    imageUrl: String? = null,
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: (Int, Int) -> Unit,
){
    Card(
        onClick = { onClick(id, partyId) },
        modifier = Modifier
            .fillMaxWidth()
            .height(162.dp)
            .padding(bottom = 2.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, GRAY100),
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
        ),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            RecruitmentCategory(
                category = category,
            )
            HeightSpacer(heightDp = 8.dp)
            RecruitmentInfoArea(
                imageUrl = imageUrl,
                title = title,
                main = main,
                sub = sub,
                recruitingCount = recruitingCount,
                recruitedCount = recruitedCount,
                onClick = { onClick(id, partyId) },
            )
        }
    }
}

@Composable
private fun RecruitmentCategory(
    category: String,
){
    Chip(
        containerColor = TYPE_COLOR_BACKGROUND,
        contentColor = TYPE_COLOR_TEXT,
        text = category,
    )
}

@Composable
private fun RecruitmentInfoArea(
    imageUrl: String?,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .height(90.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RecruitmentImage(
            imageUrl = imageUrl,
        )
        WidthSpacer(widthDp = 12.dp)

        RecruitmentContent(
            title = title,
            main = main,
            sub = sub,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            onClick = onClick,
        )
    }
}

@Composable
private fun RecruitmentImage(
    imageUrl: String?,
) {
    ImageLoading(
        modifier = Modifier
            .width(120.dp)
            .height(90.dp),
        imageUrl = imageUrl,
        roundedCornerShape = LARGE_CORNER_SIZE
    )
}

@Composable
private fun RecruitmentContent(
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: () -> Unit,
){
    Column(
        modifier = Modifier
            .width(195.dp)
            .height(90.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CustomText(
                text = title,
            )
            HeightSpacer(heightDp = 5.dp)
            MainAndSubPosition(
                modifier = Modifier
                    .height(20.dp),
                main = main,
                sub = sub,
                onClick = onClick,
            )
        }

        HeightSpacer(heightDp = 5.dp)

        RecruitmentCountingSection(
            modifier = Modifier
                .wrapContentHeight()
            ,
            horizontalArrangement = Arrangement.Start,
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            onClick = onClick
        )
    }
}