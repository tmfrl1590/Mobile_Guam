package com.party.guham2.design.component.empty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY500
import com.party.guham2.design.component.util.HeightSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyContentSection(
    modifier: Modifier = Modifier,
    title: String,
){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(resource = DesignResources.Icon.icon_info),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp),
        )
        HeightSpacer(heightDp = 6.dp)
        Text(
            text = title,
            fontSize = B1,
            fontWeight = FontWeight.SemiBold,
            color = GRAY500,
        )
    }
}