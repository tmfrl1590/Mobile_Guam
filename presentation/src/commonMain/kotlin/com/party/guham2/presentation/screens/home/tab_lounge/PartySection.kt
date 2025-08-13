package com.party.guham2.presentation.screens.home.tab_lounge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY200
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.PartyCard1
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.chip.IconChip
import com.party.guham2.design.component.chip.OrderByCreateDtChip
import com.party.guham2.design.component.empty.EmptyContentSection
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.toggle.IngToggle
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.type.StatusType
import com.party.guham2.presentation.model.party.PartyItemModel
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.presentation.screens.home.state.PartyState
import org.jetbrains.compose.resources.painterResource

@Composable
fun PartySection(
    gridState: LazyGridState,
    partyState: PartyState,
    onClickChip: () -> Unit,
    selectedPartyTypeCount: Int,
    onToggle: (Boolean) -> Unit,
    onChangeOrderByPartySection: (Boolean) -> Unit,
    onClickPartyCard: (Int) -> Unit,
){
    Column {
        PartyControlSection(
            onClickChip = onClickChip,
            selectedPartyTypeCount = selectedPartyTypeCount,
            isChecked = partyState.isOnTogglePartySection,
            onToggle = onToggle,
            selectedCreateDateOrderByDesc = partyState.isDescPartySection,
            onChangeOrderByPartySection = onChangeOrderByPartySection
        )

        HeightSpacer(heightDp = 12.dp)

        PartyListSection(
            gridState = gridState,
            partyList = partyState.partyList,
            onClickPartyCard = onClickPartyCard
        )
    }
}

@Composable
private fun PartyControlSection(
    onClickChip: () -> Unit,
    selectedPartyTypeCount: Int,
    isChecked: Boolean,
    onToggle: (Boolean) -> Unit,
    selectedCreateDateOrderByDesc: Boolean,
    onChangeOrderByPartySection: (Boolean) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconChip(
                text = "파티유형",
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
                painter = painterResource(DesignResources.Icon.icon_arrow_down),
                onClickChip = onClickChip
            )

            IngToggle(
                isChecked = isChecked,
                onToggle = onToggle
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OrderByCreateDtChip(
                text = "등록일순",
                orderByDesc = selectedCreateDateOrderByDesc,
                onChangeSelected = { onChangeOrderByPartySection(it) }
            )
        }
    }
}

@Composable
private fun PartyListSection(
    gridState: LazyGridState,
    partyList: List<PartyItemModel>,
    onClickPartyCard: (Int) -> Unit,
){
    if(partyList.isEmpty()){
        HeightSpacer(heightDp = 76.dp)
        EmptyContentSection(
            title = "파티가 없어요."
        )
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(bottom = 10.dp),
            state = gridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(
                items = partyList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                PartyCard1(
                    imageUrl = item.image,
                    statusChip = {
                        Chip(
                            containerColor = StatusType.fromType(item.status).toContainerColor(),
                            contentColor = StatusType.fromType(item.status).toContentColor(),
                            text = StatusType.fromType(item.status).toDisplayText()
                        )
                    },
                    partyTypeChip = {
                        Chip(
                            containerColor = TYPE_COLOR_BACKGROUND,
                            contentColor = TYPE_COLOR_TEXT,
                            text = item.partyType.type
                        )
                    },
                    title = item.title,
                    recruitmentCount = item.recruitmentCount,
                    onClick = { onClickPartyCard(item.id) }
                )
            }
        }
    }
}