package com.party.guham2.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.GRAY100
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.T3
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable

@Composable
fun PartyCard2(
    imageUrl: String? = null,
    status: String,
    statusContainerColor: Color,
    statusContentColor: Color,
    partyType: String,
    title: String,
    main: String,
    sub: String,
    onClick: () -> Unit,
){
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
        ,
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, GRAY100),
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
        ),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PartyImageSection(
                imageUrl = imageUrl,
            )

            WidthSpacer(widthDp = 12.dp)
            PartyInfoArea(
                status = status,
                statusContainerColor = statusContainerColor,
                statusContentColor = statusContentColor,
                partyType = partyType,
                title = title,
                main = main,
                sub = sub,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun PartyImageSection(
    imageUrl: String? = null,
) {
    ImageLoading(
        modifier = Modifier
            .width(120.dp)
            .height(90.dp),
        imageUrl = imageUrl,
        roundedCornerShape = LARGE_CORNER_SIZE
    )
}

@Composable
private fun PartyInfoArea(
    status: String,
    statusContainerColor: Color,
    statusContentColor: Color,
    partyType: String,
    title: String,
    main: String,
    sub: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .noRippleClickable(onClick = onClick)
    ) {
        PartyCategoryArea(
            status = status,
            statusContainerColor = statusContainerColor,
            statusContentColor = statusContentColor,
            partyType = partyType,
        )
        HeightSpacer(heightDp = 8.dp)

        PartyTitleArea(
            title = title,
        )
        HeightSpacer(heightDp = 8.dp)
        PartyPositionArea(
            main = main,
            sub = sub,
        )
    }
}

@Composable
private fun PartyCategoryArea(
    status: String,
    statusContainerColor: Color,
    statusContentColor: Color,
    partyType: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Chip(
            containerColor = statusContainerColor,
            contentColor = statusContentColor,
            text = status,
        )
        WidthSpacer(widthDp = 4.dp)
        Chip(
            containerColor = TYPE_COLOR_BACKGROUND,
            contentColor = TYPE_COLOR_TEXT,
            text = partyType,
        )
    }
}

@Composable
private fun PartyTitleArea(
    title: String,
) {
    CustomText(
        text = title,
        fontSize = T3,
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun PartyPositionArea(
    main: String,
    sub: String,
) {
    CustomText(
        modifier = Modifier
            .fillMaxWidth(),
        text = "$main | $sub",
        fontSize = B2,
    )
}