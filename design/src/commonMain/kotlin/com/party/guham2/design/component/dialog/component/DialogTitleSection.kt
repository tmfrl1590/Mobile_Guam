package com.party.guham2.design.component.dialog.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.party.guham2.design.component.text.CustomText

@Composable
fun DialogTitleSection(
    dialogTitle: String,
){
    CustomText(
        modifier = Modifier.fillMaxWidth(),
        text = dialogTitle,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}