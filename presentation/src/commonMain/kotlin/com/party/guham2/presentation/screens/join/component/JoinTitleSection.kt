package com.party.guham2.presentation.screens.join.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.T2
import com.party.guham2.design.component.header.CenterTopAppBar
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun JoinTitleSection(
    onNavigateBack: () -> Unit = {},
    title: String = "회원가입",
    actionIcon: @Composable () -> Unit = {}
){
    CenterTopAppBar(
        navigationIcon = {
            Row {
                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
                Icon(
                    painter = painterResource(resource = DesignResources.Icon.icon_arrow_back),
                    contentDescription = "back",
                    tint = BLACK,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable {
                            onNavigateBack()
                        }
                )
            }

        },
        title = {
            CustomText(
                text = title,
                fontSize = T2,
                fontWeight = FontWeight.Bold
            )
        },
        actionIcons = {
            actionIcon()
        }
    )
}