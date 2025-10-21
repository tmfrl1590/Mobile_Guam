package com.party.guham2.design.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY100
import com.party.guham2.design.GRAY200
import com.party.guham2.design.GRAY500
import com.party.guham2.design.GRAY600
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.T3
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.design.util.convertIsoToCustomDateFormat
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecruitmentCard3(
    date: String,
    status: String,
    imageUrl: String? = null,
    activeOrComplete: String,
    statusColor: Color,
    partyType: String,
    title: String,
    main: String,
    sub: String,
    content: String,
    onClick: () -> Unit,
    onRefusal: () -> Unit,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
){
    var isContentVisible by remember { mutableStateOf(true) }

    Card(
        onClick = { if(activeOrComplete == "active") onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        border = BorderStroke(1.dp, GRAY100),
        colors =
            CardDefaults.cardColors(
                containerColor = WHITE,
            ),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        ) {
            RecruitmentDataAndState(
                applyDate = date,
                status = status,
                statusColor = statusColor
            )
            HeightSpacer(heightDp = 12.dp)
            RecruitmentInfoArea(
                activeOrComplete = activeOrComplete,
                imageUrl = imageUrl,
                partyType = partyType,
                title = title,
                main = main,
                sub = sub,
            )


            if(activeOrComplete == "completed"){
                HeightSpacer(heightDp = 12.dp)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = GRAY100,
                    ),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "모집 마감된 공고에요.",
                            fontWeight = FontWeight.SemiBold,
                            color = GRAY600,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // 애니메이션 영역
            AnimatedVisibility(visible = isContentVisible) {
                Column {
                    HeightSpacer(heightDp = 20.dp)
                    RecruitmentContent(content = content)
                    HeightSpacer(heightDp = 12.dp)

                    when(status){
                        "응답대기" -> {
                            if (activeOrComplete == "active" || activeOrComplete == "processing") {
                                CancelAndApplyButtonArea(
                                    onRefusal = onRefusal,
                                    onAccept = onAccept,
                                )
                            }
                        }
                        "검토중" -> {
                            CancelButton(
                                onCancel = onCancel
                            )
                        }
                    }
                }
            }

            HeightSpacer(heightDp = 20.dp)
            HorizontalDivider(color = GRAY200)

            ChangeApplicationFormVisible(
                isContentVisible = isContentVisible,
                onToggle = { isContentVisible = !isContentVisible },
            )
        }
    }
}

@Composable
private fun RecruitmentDataAndState(
    applyDate: String,
    status: String,
    statusColor: Color,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(17.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CustomText(
            text = "지원일 ${convertIsoToCustomDateFormat(applyDate)}",
            fontSize = B3,
            color = GRAY500,
        )

        CustomText(
            text = status,
            fontSize = B3,
            color = statusColor,
        )
    }
}

@Composable
private fun RecruitmentInfoArea(
    activeOrComplete: String,
    imageUrl: String? = null,
    partyType: String,
    title: String,
    main: String,
    sub: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RecruitmentImageArea(
            activeOrComplete = activeOrComplete,
            imageUrl = imageUrl,
        )
        WidthSpacer(widthDp = 12.dp)
        RecruitmentContent(
            activeOrComplete = activeOrComplete,
            partyType = partyType,
            title = title,
            main = main,
            sub = sub,
        )
    }
}

@Composable
private fun RecruitmentImageArea(
    activeOrComplete: String,
    imageUrl: String? = null
) {
    if(activeOrComplete == "completed"){
        Box(
            modifier = Modifier.wrapContentSize() // Chip 크기만큼 Box 크기를 조정
        ) {
            ImageLoading(
                modifier = Modifier
                    .width(120.dp)
                    .height(90.dp),
                imageUrl = imageUrl,
                roundedCornerShape = LARGE_CORNER_SIZE,
            )

            // 투명한 레이어
            Box(
                modifier = Modifier
                    .matchParentSize() // 부모(Box)와 동일한 크기
                    .background(Color(0xB3FFFFFF)) // 흰색(70% 투명)
            )
        }
    }else{
        ImageLoading(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp),
            imageUrl = imageUrl,
            roundedCornerShape = LARGE_CORNER_SIZE,
        )
    }
}

@Composable
private fun RecruitmentContent(
    activeOrComplete: String,
    partyType: String,
    title: String,
    main: String,
    sub: String,
) {
    Column {
        RecruitmentCategory(
            activeOrComplete = activeOrComplete,
            partyType = partyType,
            containerColor = TYPE_COLOR_BACKGROUND,
            contentColor = TYPE_COLOR_TEXT,
            /*partyType = partyType,
            containerColor = if(activeOrComplete == "completed") Color(0xB3F6F6F6) else TYPE_COLOR_BACKGROUND,
            contentColor = if(activeOrComplete == "completed") Color(0xff454545) else TYPE_COLOR_TEXT,*/
        )
        HeightSpacer(heightDp = 8.dp)
        RecruitmentTitle(
            title = title,
            textColor = if(activeOrComplete == "completed") GRAY500 else BLACK
        )
        HeightSpacer(heightDp = 8.dp)

        RecruitmentPositionArea(
            main = main,
            sub = sub,
            textColor = if(activeOrComplete == "completed") GRAY500 else BLACK
        )
    }
}

@Composable
private fun RecruitmentCategory(
    activeOrComplete: String,
    partyType: String,
    containerColor: Color,
    contentColor: Color,
) {
    if(activeOrComplete == "completed"){
        Box(
            modifier = Modifier.wrapContentSize() // Chip 크기만큼 Box 크기를 조정
        ) {
            // Chip 컴포넌트
            Chip(
                containerColor = containerColor,
                contentColor = contentColor,
                text = partyType,
            )

            // 투명한 레이어
            Box(
                modifier = Modifier
                    .matchParentSize() // 부모(Box)와 동일한 크기
                    .background(Color(0xB3FFFFFF)) // 흰색(70% 투명)
            )
        }
    }else{
        Chip(
            containerColor = containerColor,
            contentColor = contentColor,
            text = partyType,
        )
    }
}

@Composable
private fun RecruitmentTitle(
    title: String,
    textColor: Color,
) {
    CustomText(
        text = title,
        fontSize = T3,
        color = textColor,
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
        maxLine = 1,
    )
}

@Composable
private fun RecruitmentPositionArea(
    textColor: Color,
    main: String,
    sub: String,
) {
    CustomText(
        modifier = Modifier.fillMaxWidth(),
        text = "$main | $sub",
        fontSize = B2,
        color = textColor
    )
}


@Composable
private fun RecruitmentContent(content: String) {
    CustomText(
        modifier =
            Modifier
                .fillMaxWidth(),
        text = content,
        fontSize = B2,
        color = GRAY600,
    )
}

// 거절하기, 수락하기 버튼 - 응답대기인 경우 보이기 (마감인경우 버튼 안보임)
@Composable
private fun CancelAndApplyButtonArea(
    onRefusal: () -> Unit,
    onAccept: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomButton(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            buttonText = "거절하기",
            buttonTextWeight = FontWeight.SemiBold,
            containerColor = WHITE,
            onClick = onRefusal,
            buttonTextSize = 12.sp
        )
        WidthSpacer(widthDp = 8.dp)
        CustomButton(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            buttonText = "수락하기",
            buttonTextWeight = FontWeight.SemiBold,
            onClick = onAccept,
            buttonTextSize = 12.sp,
        )
    }
}

@Composable
private fun ChangeApplicationFormVisible(
    isContentVisible: Boolean,
    onToggle: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .noRippleClickable { onToggle() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        CustomText(
            text = if (isContentVisible) "지원서 닫기" else "지원서 열기",
            fontSize = B3,
            color = GRAY600,
            modifier = Modifier
                .clickable { onToggle()},
        )
        WidthSpacer(widthDp = 4.dp)
        Icon(
            modifier = Modifier.size(16.dp),
            painter = if (isContentVisible) painterResource(DesignResources.Icon.icon_arrow_up_long) else painterResource(DesignResources.Icon.icon_arrow_down),
            tint = GRAY500,
            contentDescription = "This is Expand Button",
        )
    }
}

// 지원 취소 - 검토중일 경우 보이기
@Composable
private fun CancelButton(
    onCancel: () -> Unit,
) {
    CustomButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        buttonText = "지원 취소",
        buttonTextWeight = FontWeight.SemiBold,
        containerColor = WHITE,
        onClick = onCancel,
        buttonTextSize = 12.sp,
    )
}