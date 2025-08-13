package com.party.guham2.design.component.toggle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY500
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun IngToggle(
    isChecked: Boolean,
    onToggle: (Boolean) -> Unit,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomText(
            text = "진행중",
            color = if (isChecked) PRIMARY else GRAY500,
        )
        WidthSpacer(widthDp = 2.dp)
        Image(
            painter = if(isChecked) painterResource(DesignResources.Icon.icon_toggle_on) else painterResource(DesignResources.Icon.icon_toggle_off),
            contentDescription = "toggle",
            modifier = Modifier
                .noRippleClickable {
                    onToggle(!isChecked)
                }
        )
    }
}