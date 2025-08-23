package com.party.guham2.presentation.screens.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY500
import com.party.guham2.design.MEDIUM_CORNER_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

@Composable
fun FloatingSection(
    modifier: Modifier,
    createPartyFloating: @Composable () -> Unit,
    navigateUpFloating: @Composable () -> Unit,
    onGotoCreateParty: () -> Unit,
    showParty: Boolean
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        if(showParty){
            FabItem(
                title = "파티 생성하기",
                onClicked = onGotoCreateParty
            )
        }

        HeightSpacer(12.dp)
        navigateUpFloating()

        HeightSpacer(12.dp)
        createPartyFloating()
    }
}

@Composable
fun FabItem(
    title: String,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(56.dp),
        shape = RoundedCornerShape(MEDIUM_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = WHITE
        ),
        border = BorderStroke(1.dp, PRIMARY)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .noRippleClickable { onClicked() }
            ,
            contentAlignment = Alignment.CenterStart,
        ){
            CustomText(
                modifier = Modifier
                    .padding(start = 20.dp),
                text = title,
                fontSize = B1,
                color = BLACK,
            )
        }
    }
}

@Composable
fun CreatePartyFloatingButton(
    modifier: Modifier = Modifier,
    isShowBlurView: Boolean,
    onClick: () -> Unit,
){
    FloatingActionButton(
        modifier = modifier
        ,
        onClick = onClick,
        shape = CircleShape,
        containerColor = if(isShowBlurView) WHITE else PRIMARY
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = if(isShowBlurView) Icons.Rounded.Close else Icons.Rounded.Add,
            tint = if (isShowBlurView) BLACK else WHITE,
            contentDescription = "This is Expand Button",
        )
    }
}

@Composable
fun NavigateUpFloatingButton(
    onClick: () -> Unit,
){
    FloatingActionButton(
        modifier = Modifier,
        onClick = onClick,
        shape = CircleShape,
        containerColor = WHITE
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(DesignResources.Icon.icon_arrow_up_long),
            tint = GRAY500,
            contentDescription = "This is Expand Button",
        )
    }
}