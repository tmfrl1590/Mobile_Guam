package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.component.header.CenterTopAppBar
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecruitmentTitleSection(
    onNavigateBack: () -> Unit = {},
    onManageRecruitmentClick: () -> Unit,
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
        actionIcons = {
            Row {
                Image(
                    painter = painterResource(resource = DesignResources.Icon.icon_setting),
                    contentDescription = "setting",
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable {
                            onManageRecruitmentClick()
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