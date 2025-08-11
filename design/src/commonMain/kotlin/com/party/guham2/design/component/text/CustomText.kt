package com.party.guham2.design.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import org.jetbrains.compose.resources.Font

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BLACK,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = B2,
){
    Text(
        modifier = modifier,
        text = text,
        fontFamily = FontFamily(Font(resource = DesignResources.Font.suit_regular)),
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}