package com.party.guham2.design.component.bottomsheet.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun ResetAndApplySection(
    modifier: Modifier = Modifier,
    onReset: () -> Unit,
    onApply: () -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomButton(
            modifier = modifier
                .weight(1f)
                .height(56.dp),
            onClick = onReset,
            buttonText = "초기화",
            containerColor = WHITE,
            buttonTextWeight = FontWeight.Bold
        )
        WidthSpacer(widthDp = 8.dp)
        CustomButton(
            modifier = modifier
                .weight(2f)
                .height(56.dp),
            onClick = onApply,
            buttonText = "적용하기",
            buttonTextWeight = FontWeight.Bold
        )
    }
}