package com.party.guham2.presentation.screens.login.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.EXTRA_LARGE_BUTTON_HEIGHT2
import com.party.guham2.design.GRAY200
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.util.WidthSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginButtonSection(){
    Column {
        SocialLoginButton(
            text = "구글 로그인",
            containerColor = WHITE,
            borderColor = GRAY200,
            painterImage = painterResource(resource = DesignResources.Icon.icon_google),
            contentDescription = "kakao",
            onClick = {

            }
        )
    }
}


@Composable
fun SocialLoginButton(
    text: String,
    containerColor: Color,
    borderColor: Color,
    painterImage: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXTRA_LARGE_BUTTON_HEIGHT2)
            .background(Color.White),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = BorderStroke(1.dp, borderColor),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = MEDIUM_PADDING_SIZE)
        ) {
            Icon(
                painter = painterImage,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )

            WidthSpacer(widthDp = 12.dp)

            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}