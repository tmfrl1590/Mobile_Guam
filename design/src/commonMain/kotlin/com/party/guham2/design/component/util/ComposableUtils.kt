package com.party.guham2.design.component.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY400
import com.party.guham2.design.RED
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun WidthSpacer(
    widthDp: Dp,
) {
    Spacer(modifier = Modifier.width(widthDp))
}

@Composable
fun HeightSpacer(
    heightDp: Dp,
) {
    Spacer(
        modifier = Modifier.height(heightDp)
    )
}

@Composable
fun MainAndSubPosition(
    modifier: Modifier = Modifier,
    main: String,
    sub: String,
    textColor: Color = BLACK,
    onClick: () -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
            .noRippleClickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = main,
            fontSize = B2,
            color = textColor,
        )
        WidthSpacer(widthDp = 6.dp)
        Image(
            painter = painterResource(DesignResources.Image.image_vertical),
            contentDescription = "",
            modifier = Modifier
                .width(1.dp)
                .height(10.dp)
                .padding(top = 2.dp)
                .background(GRAY400)
            ,
        )
        WidthSpacer(widthDp = 2.dp)
        Text(
            text = sub,
            fontSize = B2,
            color = textColor,
        )
    }
}

@Composable
fun RecruitmentCountingArea(
    modifier: Modifier,
    recruitingCount: Int,
    recruitedCount: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    onClick: () -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
    ) {
        Text(
            text = "모집중",
            fontSize = B3,
        )

        WidthSpacer(widthDp = 4.dp)

        Text(
            text = "$recruitedCount / $recruitingCount",
            fontSize = B3,
            color = RED
        )
    }
}