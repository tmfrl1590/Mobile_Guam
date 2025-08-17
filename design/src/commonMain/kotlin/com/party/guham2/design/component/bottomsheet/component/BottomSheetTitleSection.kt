package com.party.guham2.design.component.bottomsheet.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.BLACK
import com.party.guham2.design.T2
import com.party.guham2.design.component.text.CustomText

@Composable
fun BottomSheetTitleSection(
    title: String,
    onClosePositionBottomSheet: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        CustomText(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            fontSize = T2,
            fontWeight = FontWeight.SemiBold,
            color = BLACK,
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(24.dp)
                .clickable { onClosePositionBottomSheet() }
        )
    }
}