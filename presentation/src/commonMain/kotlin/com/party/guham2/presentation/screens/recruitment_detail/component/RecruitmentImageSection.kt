package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY100
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.NetworkImageLoad
import com.party.guham2.design.T2
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.modifier.noRippleClickable

@Composable
fun RecruitmentImageSection(
    imageUrl: String?,
    title: String,
    tag: String,
    type: String,
    onGoToPartyDetail: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = MEDIUM_PADDING_SIZE),
    ) {
        // 카드 생성 (테두리는 카드 외부에만 적용됨)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, GRAY100), // 카드의 테두리
        ) {
            // 카드 내부의 컨텐츠
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NetworkImageLoad(
                    url = imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            onGoToPartyDetail()
                        }
                )

                // 하단 그라데이션 오버레이
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // 그라데이션 높이
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )

                CustomText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(start = 20.dp, bottom = 16.dp)
                        .noRippleClickable {
                            onGoToPartyDetail()
                        }
                    ,
                    text = title,
                    fontSize = T2,
                    color = BLACK,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        RecruitmentType(
            tag = tag,
            recruitmentType = type
        )
    }
}