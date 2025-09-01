package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.T2
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.type.StatusType

@Composable
fun RecruitmentTitle(
    title: String,
) {
    CustomText(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp),
        text = title,
        fontSize = T2,
        fontWeight = FontWeight.Bold,
        color = BLACK,
    )
}


@Composable
fun RecruitmentType(
    tag: String,
    recruitmentType: String,
){
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, top = 16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        RecruitmentCategory(
            title = StatusType.fromType(tag).toDisplayText(),
            containerColor = Color(0xFFD5F0E3),
            contentColor = Color(0xFF016110)
        )

        RecruitmentCategory(
            title = recruitmentType,
            containerColor = Color(0xFFF6F6F6),
            contentColor = Color(0xFF454545)
        )
    }
}

@Composable
private fun RecruitmentCategory(
    title: String,
    containerColor: Color,
    contentColor: Color
) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ){
            CustomText(
                text = title,
                fontSize = B3,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}