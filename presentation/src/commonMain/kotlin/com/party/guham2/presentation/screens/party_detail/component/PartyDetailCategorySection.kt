package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun PartyDetailCategorySection(
    modifier: Modifier,
    tag: String,
    partyType: String,
    statusContainerColor: Color,
    statusContentColor: Color
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Chip(
            containerColor = statusContainerColor,
            contentColor = statusContentColor,
            text = tag,
        )
        WidthSpacer(widthDp = 8.dp)
        Chip(
            containerColor = TYPE_COLOR_BACKGROUND,
            contentColor = TYPE_COLOR_TEXT,
            text = partyType,
        )
    }
}