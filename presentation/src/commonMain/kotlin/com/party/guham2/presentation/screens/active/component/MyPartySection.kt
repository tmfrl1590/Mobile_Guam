package com.party.guham2.presentation.screens.active.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.component.PartyCard2
import com.party.guham2.design.component.chip.OrderByCreateDtChip
import com.party.guham2.design.component.empty.NoDataColumn
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.LoadingProgressBar
import com.party.guham2.design.type.StatusType
import com.party.guham2.presentation.model.user.party.PartyUserModel
import com.party.guham2.presentation.screens.active.state.MyPartyState

@Composable
fun MyPartySection(
    listState: LazyListState,
    myPartyState: MyPartyState,
    onChangeOrderBy: (Boolean) -> Unit,
    onSelectStatus: (String) -> Unit,
    onClick: (Int) -> Unit,
){
    val filteredList = if (myPartyState.selectedStatus == "전체") {
        myPartyState.myPartyList.partyUsers.filterNot {
            it.party.status == "deleted"
        } // 전체 리스트 반환
    } else {
        myPartyState.myPartyList.partyUsers.filter {
            it.party.status == StatusType.fromDisplayText(myPartyState.selectedStatus)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SelectCategoryArea(
            categoryList = listOf("전체", "진행중", "종료"),
            selectedCategory = myPartyState.selectedStatus,
            onClick = onSelectStatus
        )

        JoinDataOrderBy(
            orderByDesc = myPartyState.orderByDesc,
            onChangeOrderBy = onChangeOrderBy
        )

        HeightSpacer(heightDp = 4.dp)

        when {
            myPartyState.isMyPartyLoading -> { LoadingProgressBar() }
            filteredList.isEmpty() -> {
                NoDataColumn(
                    title = "파티가 없어요",
                    modifier = Modifier
                        .padding(top = 50.dp)
                )
            }
            else -> {
                MyPartyList(
                    listState = listState,
                    filteredList = filteredList,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun JoinDataOrderBy(
    orderByDesc: Boolean,
    onChangeOrderBy: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.End
    ) {
        OrderByCreateDtChip(
            text = "참여일순",
            orderByDesc = orderByDesc,
            onChangeSelected = { onChangeOrderBy(it) },
        )
    }
}

@Composable
private fun MyPartyList(
    listState: LazyListState,
    filteredList: List<PartyUserModel>,
    onClick: (Int) -> Unit,
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        itemsIndexed(
            items = filteredList,
            key = { index, _ ->
                index
            }
        ){ _, item ->
            PartyCard2(
                imageUrl = item.party.image,
                status = StatusType.fromType(item.party.status).toDisplayText(),
                statusContainerColor = StatusType.fromType(item.party.status).toContainerColor(),
                statusContentColor = StatusType.fromType(item.party.status).toContentColor(),
                partyType = item.party.partyType.type,
                title = item.party.title,
                main = item.position.main,
                sub = item.position.sub,
                onClick = { onClick(item.party.id) }
            )
        }
    }
}