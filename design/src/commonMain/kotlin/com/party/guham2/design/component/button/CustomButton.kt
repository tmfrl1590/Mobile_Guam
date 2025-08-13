package com.party.guham2.design.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.component.text.CustomText

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    buttonTextSize: TextUnit = B2,
    buttonTextWeight: FontWeight = FontWeight(400),
    buttonTextColor: Color = BLACK,
    radius: Dp = LARGE_CORNER_SIZE,
    borderWidth: Dp = 1.dp,
    borderColor: Color = PRIMARY,
    containerColor: Color = PRIMARY,
){
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(size = radius),
        border = BorderStroke(width = borderWidth, color = borderColor),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CustomText(
                text = buttonText,
                color = buttonTextColor,
                fontSize = buttonTextSize,
                fontWeight = buttonTextWeight,
            )
        }
    }
}