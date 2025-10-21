package com.party.guham2.presentation.screens.active.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.component.header.CenterTopAppBar
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun ActiveTitleSection(
    onGoToSearch: () -> Unit,
    onGotoNotification: () -> Unit,
){
    CenterTopAppBar(
        navigationIcon = {
            Row {
                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
                CustomText(
                    text = "활동",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        actionIcons = {
            Row {
                Image(
                    painter = painterResource(DesignResources.Icon.icon_search),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable {
                            onGoToSearch()
                        }
                )
                WidthSpacer(widthDp = 12.dp)
                Image(
                    painter = painterResource(DesignResources.Icon.icon_alarm),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable {
                            onGotoNotification()
                        }
                )
                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )
            }

        }
    )
}