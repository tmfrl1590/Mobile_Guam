package com.party.guham2.design.component.dialog.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer

@Composable
fun DialogDescriptionSection(
    modifier: Modifier = Modifier,
    description: String,
){
    HeightSpacer(heightDp = 24.dp)

    CustomText(
        modifier = modifier
            .fillMaxWidth(),
        text = description,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
    )
}