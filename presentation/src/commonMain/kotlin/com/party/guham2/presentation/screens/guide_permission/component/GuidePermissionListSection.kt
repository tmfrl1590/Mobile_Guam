package com.party.guham2.presentation.screens.guide_permission.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY100
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun GuidePermissionListSection(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        GuidePermissionItem()
    }
}

@Composable
private fun GuidePermissionItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        PermissionImage(
            painter = painterResource(DesignResources.Icon.icon_alarm),
            description = "alarm",
        )
        WidthSpacer(12.dp)

        PermissionItem(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            permissionItemTitle = "알림 (선택)",
            permissionitemDescription = "파티 모집, 마감, 참여 등 정보 안내"
        )
    }
}

@Composable
private fun PermissionImage(
    painter: Painter,
    description: String,
) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(CircleShape)
            .background(GRAY100),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painter,
            contentDescription = description,
            modifier = Modifier
                .size(24.dp),
            colorFilter = ColorFilter.tint(BLACK)
        )
    }
}


@Composable
private fun PermissionItem(
    modifier: Modifier,
    permissionItemTitle: String,
    permissionitemDescription: String,
) {
    Column(
        modifier = modifier
    ) {
        CustomText(
            text = permissionItemTitle,
            fontWeight = FontWeight.SemiBold,
            fontSize = B2
        )
        HeightSpacer(4.dp)
        CustomText(
            text = permissionitemDescription,
            fontWeight = FontWeight.Normal,
            fontSize = B2
        )
    }
}