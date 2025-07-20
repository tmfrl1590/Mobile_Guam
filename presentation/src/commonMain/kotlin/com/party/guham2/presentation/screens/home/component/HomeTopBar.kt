package com.party.guham2.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.design.component.util.WidthSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeTopBar(
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HomeTopLogoImage()

        HomeTopBarIconArea(
            onGoToSearch = onGoToSearch,
            onGoToAlarm = onGoToAlarm
        )
    }
}

@Composable
private fun HomeTopLogoImage(){
    Image(
        painter = painterResource(DesignResources.Image.image_home_logo),
        contentDescription = "logo",
        modifier = Modifier
            .width(91.dp)
            .height(34.dp)
    )
}

@Composable
private fun HomeTopBarIconArea(
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
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
            contentDescription = "alarm",
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable {
                    onGoToAlarm()
                }
        )
    }
}