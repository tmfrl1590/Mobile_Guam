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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY400
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.RED
import com.party.guham2.design.T2
import com.party.guham2.design.T3
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        CustomText(
            text = main,
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
        WidthSpacer(widthDp = 6.dp)
        CustomText(
            text = sub,
            color = textColor,
        )
    }
}

@Composable
fun RecruitmentCountingSection(
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
        CustomText(
            text = "모집중",
            fontSize = B3,
        )

        WidthSpacer(widthDp = 4.dp)

        CustomText(
            text = "$recruitedCount / $recruitingCount",
            fontSize = B3,
            color = RED
        )
    }
}

@Composable
fun PartyCountingSection(
    recruitmentCount: Int,
){
    CustomText(
        text = stringResource(DesignResources.String.party_recruitment_count, recruitmentCount),
        fontSize = B3,
        color = PRIMARY,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ExplainSection(
    mainExplain: String,
    subExplain: String,
){
    HeightSpacer(heightDp = 32.dp)

    CustomText(
        text = mainExplain,
        fontWeight = FontWeight.Bold,
        fontSize = T2,
    )

    HeightSpacer(heightDp = 12.dp)

    CustomText(
        text = subExplain,
        fontSize = T3,
    )
}