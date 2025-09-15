package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.T2
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun PartyDetailTitle(
    modifier: Modifier = Modifier,
    title: String,
    number: String = "",
    progressContent: @Composable () -> Unit = {},
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            CustomText(
                text = title,
                fontSize = T2,
                fontWeight = FontWeight.Bold,
            )
            WidthSpacer(widthDp = 6.dp)
            CustomText(
                text = number,
                fontSize = T2,
                fontWeight = FontWeight.Bold,
                color = PRIMARY
            )
        }

        progressContent()
    }
}