package com.party.guham2.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.B2
import com.party.guham2.design.GRAY500
import com.party.guham2.design.T2
import com.party.guham2.design.T3
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable

@Composable
fun HomeSectionHeaderBar(
    title: String,
    description: String,
    actionText: String,
    actionIcon: Painter,
    onClickActionIcon: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
        ,
    ) {
        HomeSectionHeaderTitleArea(
            modifier = Modifier.height(25.dp),
            title = title,
            actionText = actionText,
            actionIcon = actionIcon,
            onClickActionIcon = onClickActionIcon
        )
        HeightSpacer(heightDp = 8.dp)
        Text(
            modifier = Modifier.height(22.dp),
            text = description,
            fontSize = T3
        )
    }
}

@Composable
private fun HomeSectionHeaderTitleArea(
    modifier: Modifier = Modifier,
    title: String,
    actionText: String,
    actionIcon: Painter,
    onClickActionIcon: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable{
                onClickActionIcon()
            }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = T2,
        )

        HomeSectionHeaderActionButton(
            actionText = actionText,
            actionIcon = actionIcon,
        )
    }
}

@Composable
fun HomeSectionHeaderActionButton(
    actionText: String,
    actionIcon: Painter,
){
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = actionText,
            fontSize = B2,
            color = GRAY500,
            fontWeight = FontWeight(400)
        )
        WidthSpacer(2.dp)
        Image(
            painter = actionIcon,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
            ,
            colorFilter = ColorFilter.tint(GRAY500)
        )
    }
}