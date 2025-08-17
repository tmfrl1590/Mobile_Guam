package com.party.guham2.presentation.screens.home.tab_lounge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.GRAY200
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.RecruitmentCard2
import com.party.guham2.design.component.chip.IconChip
import com.party.guham2.design.component.chip.OrderByCreateDtChip
import com.party.guham2.design.component.empty.EmptyContentSection
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel
import com.party.guham2.presentation.screens.home.state.RecruitmentState

@Composable
fun RecruitmentSection(
    listState: LazyListState,
    recruitmentState: RecruitmentState,
    onClickRecruitmentChip: () -> Unit,
    onClickChip: () -> Unit,
    onChangeOrderByPartySection: (Boolean) -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeightSpacer(heightDp = 20.dp)
        RecruitmentControlSection(
            selectedPositionCount = recruitmentState.selectedPositionCount,
            selectedPartyTypeCount = recruitmentState.selectedPartyTypeCount,
            onClickRecruitmentChip = onClickRecruitmentChip,
            onClickChip = onClickChip,
            selectedCreateDateOrderByDesc = recruitmentState.isDescPartySection,
            onChangeOrderByPartySection = onChangeOrderByPartySection,
        )
        HeightSpacer(heightDp = 16.dp)
        RecruitmentListSection(
            listState = listState,
            recruitmentList = recruitmentState.recruitmentList,
            onClickRecruitmentCard = onClickRecruitmentCard,
        )
    }
}

@Composable
private fun RecruitmentControlSection(
    selectedPositionCount: Int,
    selectedPartyTypeCount: Int,
    onClickRecruitmentChip: () -> Unit,
    onClickChip: () -> Unit,
    selectedCreateDateOrderByDesc: Boolean,
    onChangeOrderByPartySection: (Boolean) -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconChip(
                text = "직무",
                selectedText = {
                    if(selectedPositionCount > 0){
                        CustomText(
                            text = selectedPositionCount.toString(),
                            color = PRIMARY,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(x = (1).dp, y = (1).dp),
                        )
                    }
                },
                borderColor = if(selectedPositionCount > 0) PRIMARY else GRAY200,
                containerColor = WHITE,
                imageVector = Icons.Filled.KeyboardArrowDown,
                onClickChip = onClickRecruitmentChip
            )
            WidthSpacer(widthDp = 8.dp)
            IconChip(
                text = "파티 유형",
                selectedText = {
                    if(selectedPartyTypeCount > 0){
                        CustomText(
                            text = selectedPartyTypeCount.toString(),
                            color = PRIMARY,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.offset(x = (1).dp, y = (1).dp),
                        )
                    }
                },
                borderColor = if(selectedPartyTypeCount > 0) PRIMARY else GRAY200,
                containerColor = WHITE,
                imageVector = Icons.Filled.KeyboardArrowDown,
                onClickChip = onClickChip
            )
        }

        OrderByCreateDtChip(
            text = "등록일순",
            orderByDesc = selectedCreateDateOrderByDesc,
            onChangeSelected = { onChangeOrderByPartySection(it) }
        )
    }
}

@Composable
private fun RecruitmentListSection(
    listState: LazyListState,
    recruitmentList: List<RecruitmentItemModel>,
    onClickRecruitmentCard: (Int, Int) -> Unit,
){
    if(recruitmentList.isEmpty()){
        HeightSpacer(heightDp = 76.dp)
        EmptyContentSection(
            title = "모집공고가 없어요."
        )
    } else {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){
            itemsIndexed(
                items = recruitmentList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                RecruitmentCard2(
                    id = item.id,
                    partyId = item.party.id,
                    imageUrl = item.party.image,
                    category = item.party.partyType.type,
                    title = item.party.title,
                    main = item.position.main,
                    sub = item.position.sub,
                    recruitingCount = item.recruitingCount,
                    recruitedCount = item.recruitedCount,
                    onClick = onClickRecruitmentCard
                )
            }
        }
    }
}