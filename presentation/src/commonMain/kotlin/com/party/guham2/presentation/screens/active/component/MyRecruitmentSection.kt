package com.party.guham2.presentation.screens.active.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY500
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.T3
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.RecruitmentCard3
import com.party.guham2.design.component.chip.OrderByCreateDtChip
import com.party.guham2.design.component.empty.NoDataColumn
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.tolltip.TriangleEdge
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.LoadingProgressBar
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.design.type.RecruitmentStatusType
import com.party.guham2.presentation.model.user.party.PartyApplicationModel
import com.party.guham2.presentation.screens.active.state.MyPartyState
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyRecruitmentSection(
    listState: LazyListState,
    myPartyState: MyPartyState,
    onSelectRecruitmentTab: (String) -> Unit,
    onShowHelpCard: (Boolean) -> Unit,
    onChangeOrderBy: (Boolean) -> Unit,
    onRefusal: (Int, Int) -> Unit,
    onAccept: (Int, Int) -> Unit,
    onCancel: (Int, Int) -> Unit,
){
    val filteredList = if (myPartyState.selectedRecruitmentStatus == "전체") {
        myPartyState.myRecruitmentList.partyApplications // 전체 리스트 반환
    } else {
        myPartyState.myRecruitmentList.partyApplications.filter {
            it.status == RecruitmentStatusType.fromDisplayText(myPartyState.selectedRecruitmentStatus)
        }
    }

    var recruitmentStateTextPosition by remember { mutableStateOf(IntOffset.Zero) }
    var recruitmentStateTextWidth by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        SelectCategoryArea(
            categoryList = listOf("전체", "검토중", "응답대기", "수락", "거절"),
            selectedCategory = myPartyState.selectedRecruitmentStatus,
            onClick = onSelectRecruitmentTab,
        )

        RecruitmentStateAndOrderByArea(
            orderByDesc = myPartyState.orderByRecruitmentDateDesc,
            onShowHelpCard = { onShowHelpCard(true) },
            onChangeOrderBy = onChangeOrderBy,
            onTextPositioned = { position, width ->
                recruitmentStateTextPosition = position
                recruitmentStateTextWidth = width
            }
        )

        HeightSpacer(heightDp = 4.dp)

        Box {
            when {
                myPartyState.isMyRecruitmentLoading -> { LoadingProgressBar() }
                filteredList.isEmpty() -> {
                    NoDataColumn(
                        title = "지원한 파티가 없어요",
                        modifier =
                            Modifier
                                .padding(top = 50.dp),
                    )
                }
                else -> {
                    MyRecruitmentList(
                        listState = listState,
                        filteredList = filteredList,
                        onRefusal = onRefusal,
                        onAccept = onAccept,
                        onCancel = onCancel
                    )
                }
            }

            if(myPartyState.isShowHelpCard){
                StateHelpCard(
                    textPosition = recruitmentStateTextPosition,
                    textWidth = recruitmentStateTextWidth,
                    onCloseHelpCard = { onShowHelpCard(false) }
                )
            }
        }
    }
}

@Composable
private fun RecruitmentStateAndOrderByArea(
    orderByDesc: Boolean,
    onShowHelpCard: () -> Unit,
    onChangeOrderBy: (Boolean) -> Unit,
    onTextPositioned: (IntOffset, Int) -> Unit, // 추가
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        RecruitmentState(
            onShowHelpCard = onShowHelpCard,
            onTextPositioned = onTextPositioned // 추가
        )

        OrderByCreateDtChip(
            text = "지원일순",
            orderByDesc = orderByDesc,
            onChangeSelected = { onChangeOrderBy(it) },
        )
    }
}

@Composable
private fun RecruitmentState(
    onShowHelpCard: () -> Unit,
    onTextPositioned: (IntOffset, Int) -> Unit, // 추가
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "지원상태")
        WidthSpacer(widthDp = 2.dp)
        Image(
            painter = painterResource(DesignResources.Icon.icon_help),
            contentDescription = "help",
            colorFilter = ColorFilter.tint(color = GRAY500),
            modifier = Modifier
                .size(16.dp)
                .noRippleClickable { onShowHelpCard() }
                .onGloballyPositioned { coordinates ->
                    val position = IntOffset(
                        x = coordinates.positionInParent().x.toInt(),
                        y = coordinates.positionInParent().y.toInt()
                    )
                    val width = coordinates.size.width
                    // 헬프 아이콘의 위치와 크기를 전달
                    onTextPositioned(position, width)
                }
        )
    }
}

@Composable
private fun MyRecruitmentList(
    listState: LazyListState,
    filteredList: List<PartyApplicationModel>,
    onRefusal: (Int, Int) -> Unit,
    onAccept: (Int, Int) -> Unit,
    onCancel: (Int, Int) -> Unit,
) {
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        itemsIndexed(
            items = filteredList,
            key = { index, _ ->
                index
            },
        ) { _, item ->
            RecruitmentCard3(
                date = item.createdAt,
                status = RecruitmentStatusType.fromStatus(item.status).toDisplayText(),
                imageUrl = item.partyRecruitment.party.image,
                activeOrComplete = item.partyRecruitment.status,
                statusColor = RecruitmentStatusType.fromStatus(item.status).toColor(),
                partyType = item.partyRecruitment.party.partyType.type,
                title = item.partyRecruitment.party.title,
                main = item.partyRecruitment.position.main,
                sub = item.partyRecruitment.position.sub,
                content = item.message,
                onClick = {},
                onRefusal = { onRefusal(item.id, item.partyRecruitment.party.id)},
                onAccept = { onAccept(item.id, item.partyRecruitment.party.id)},
                onCancel = { onCancel(item.partyRecruitment.party.id, item.id) }
            )
        }
    }
}

@Composable
private fun StateHelpCard(
    textPosition: IntOffset,
    textWidth: Int,
    onCloseHelpCard: () -> Unit,
) {
    val density = LocalDensity.current
    // 텍스트 중앙 위치 계산 (dp로 변환)
    val triangleOffsetX = with(density) {
        (textPosition.x + textWidth / 2).toDp() - 5.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .offset(x = triangleOffsetX)
                .width(10.dp)
                .height(5.dp)
                .background(color = GRAY500, shape = TriangleEdge()),
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            shape = RoundedCornerShape(LARGE_CORNER_SIZE),
            colors = CardDefaults.cardColors(
                containerColor = GRAY500,
            ),
            border = BorderStroke(1.dp, GRAY500),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CustomText(
                        text = "상태에 대해 알려드릴게요",
                        fontSize = T3,
                        color = WHITE,
                        fontWeight = FontWeight.Bold,
                    )
                    Icon(
                        modifier = Modifier.size(16.dp).noRippleClickable { onCloseHelpCard()},
                        painter = painterResource(resource = DesignResources.Icon.icon_close2),
                        contentDescription = "close icon",
                        tint = WHITE
                    )
                }
                HeightSpacer(heightDp = 12.dp)
                CustomText(
                    text = "검토중 : 파티장이 지원서 확인 전이에요.",
                    fontSize = B2,
                    color = WHITE,
                )
                CustomText(
                    text = "응답대기 : 파티장 수락 후, 지원자의 수락을 기다려요.",
                    fontSize = B2,
                    color = WHITE,
                )
                CustomText(
                    text = "수락 : 파티장과 지원자 모두 수락했어요.",
                    fontSize = B2,
                    color = WHITE,
                )
                CustomText(
                    text = "거절 : 파티장 또는 지원자가 거절했어요.",
                    fontSize = B2,
                    color = WHITE,
                )

                HeightSpacer(heightDp = 20.dp)
                CustomText(
                    text = "일주일 이내 수락하지 않으면 거절됩니다.",
                    fontSize = B2,
                    color = PRIMARY,
                )

                HeightSpacer(heightDp = 20.dp)
                CustomText(
                    text = "지원목록은 지원일 기준 30일까지 보관됩니다.",
                    fontSize = B2,
                    color = WHITE,
                )

                HeightSpacer(heightDp = 12.dp)
            }
        }
    }
}