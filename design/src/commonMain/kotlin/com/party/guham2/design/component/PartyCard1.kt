package com.party.guham2.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.PartyCountingSection
import com.party.guham2.design.type.StatusType

@Composable
fun PartyCard1(
    imageUrl: String? = null,
    status: String,
    title: String,
    recruitmentCount: Int,
    onClick: () -> Unit,
){
    Card(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = WHITE
        ),
        border = BorderStroke(1.dp, GRAY100),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .height(295.dp)
                .padding(12.dp),
        ) {
            PartyItemTopSection(
                imageUrl = imageUrl,
            )
            HeightSpacer(heightDp = 12.dp)
            PartyItemBottomSection(
                status = status,
                title = title,
                recruitmentCount = recruitmentCount,
            )
        }
    }
}

@Composable
private fun PartyItemTopSection(
    imageUrl: String? = null,
) {
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        imageUrl = imageUrl,
        roundedCornerShape = MEDIUM_CORNER_SIZE,
    )
}

@Composable
private fun PartyItemBottomSection(
    status: String,
    title: String,
    recruitmentCount: Int,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
    ) {
        Chip(
            containerColor = StatusType.fromType(status).toContainerColor(),
            contentColor = StatusType.fromType(status).toContentColor(),
            text = StatusType.fromType(status).toDisplayText()
        )
        HeightSpacer(heightDp = 4.dp)
        CustomText(
            text = title,
            fontSize = T3,
            fontWeight = FontWeight.Bold,
        )
        HeightSpacer(heightDp = 12.dp)
        PartyCountingSection(
            recruitmentCount = recruitmentCount,
        )
    }
}