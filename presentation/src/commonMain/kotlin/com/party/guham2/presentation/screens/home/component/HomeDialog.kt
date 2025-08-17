package com.party.guham2.presentation.screens.home.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.component.bottomsheet.SelectPartyTypeBottomSheet
import com.party.guham2.design.component.bottomsheet.SelectPositionBottomSheet
import com.party.guham2.presentation.model.user.PositionItemModel

@Composable
fun HomeDialog(
    isShowPartyTypeBottomSheet: Boolean,
    selectedPartyTypeList: List<String>,
    onClickPartyType: (String) -> Unit,
    onCloseBottomSheet: () -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
    isShowPositionBottomSheet: Boolean,
    selectedMainPosition: String,
    selectedSubPositionList: List<Pair<String, Int>>,
    onClickMainPosition: (String) -> Unit,
    onClickSubPosition: (String) -> Unit,
    onClosePositionBottomSheet: () -> Unit,
    subPositionList: List<PositionItemModel>,
    selectedMainAndSubPositionList: List<Pair<String, String>>,
    onDelete: (Pair<String, String>) -> Unit,
    onResetSelectedPosition: () -> Unit = {},
    onApplySelectedPosition: () -> Unit,
){
    if(isShowPartyTypeBottomSheet){
        SelectPartyTypeBottomSheet(
            title = "파티 유형",
            selectedPartyTypeList = selectedPartyTypeList,
            onClickPartyType = onClickPartyType,
            onCloseBottomSheet = onCloseBottomSheet,
            onReset = onReset,
            onApply = onApply
        )
    }

    if(isShowPositionBottomSheet){
        SelectPositionBottomSheet(
            selectedMainPosition = selectedMainPosition,
            subPositionList = subPositionList.map { it.sub to it.id },
            selectedSubPositionList = selectedSubPositionList,
            selectedMainAndSubPositionList = selectedMainAndSubPositionList,
            onClosePositionBottomSheet = onClosePositionBottomSheet,
            onClickMainPosition = onClickMainPosition,
            onClickSubPosition = onClickSubPosition,
            onDelete = onDelete,
            onReset = onResetSelectedPosition,
            onApply = onApplySelectedPosition,
        )
    }
}