package com.party.guham2.design.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.guham2.design.GRAY500
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer

@Composable
fun IconChip(
    text: String,
    selectedText: (@Composable () -> Unit)? = null,
    spacer: Dp = 2.dp,
    imageVector: ImageVector,
    iconSize: Dp = 16.dp,
    containerColor: Color,
    borderColor: Color,
    onClickChip: () -> Unit,
){
    Card(
        onClick = { onClickChip() },
        shape = RoundedCornerShape(999.dp),
        modifier = Modifier
            .wrapContentWidth()
            .height(36.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomText(
                text = text,
            )
            WidthSpacer(widthDp = spacer)
            selectedText?.let {
                it()
                WidthSpacer(widthDp = spacer)
            }

            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = GRAY500,
                modifier = Modifier
                    .size(iconSize)
            )
        }
    }
}