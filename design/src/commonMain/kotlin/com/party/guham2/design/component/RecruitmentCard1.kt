package com.party.guham2.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.GRAY100
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.MEDIUM_CORNER_SIZE
import com.party.guham2.design.T3
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.MainAndSubPosition
import com.party.guham2.design.component.util.RecruitmentCountingArea

@Composable
fun RecruitmentCard1(
    imageUrl: String? = null,
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: () -> Unit,
){
    Card(
        onClick = onClick,
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = WHITE
        ),
        border = BorderStroke(1.dp, GRAY100),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .width(224.dp)
                .height(311.dp)
                .padding(12.dp),
        ) {
            RecruitmentItemTopArea(
                imageUrl = imageUrl
            )
            HeightSpacer(heightDp = 12.dp)

            RecruitmentItemBottomArea(
                category = category,
                title = title,
                main = main,
                sub = sub,
                recruitingCount = recruitingCount,
                recruitedCount = recruitedCount,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun RecruitmentItemTopArea(
    imageUrl: String? = null,
) {
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        imageUrl = imageUrl,
        roundedCornerShape = MEDIUM_CORNER_SIZE
    )
}

@Composable
fun RecruitmentItemBottomArea(
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Chip(
                containerColor = TYPE_COLOR_BACKGROUND,
                contentColor = TYPE_COLOR_TEXT,
                text = category,
            )
            HeightSpacer(heightDp = 4.dp)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 6.dp)
                ,
                text = title,
                fontSize = T3,
                fontWeight = FontWeight.Bold,
            )
            HeightSpacer(heightDp = 4.dp)

            MainAndSubPosition(
                modifier = Modifier
                    .padding(start = 6.dp)
                ,
                main = main,
                sub = sub,
                onClick = onClick
            )
        }

        RecruitmentCountingArea(
            modifier = Modifier
                .wrapContentHeight(),
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            onClick = onClick
        )
    }
}