package com.party.guham2.presentation.screens.home.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.component.bottomsheet.SelectPartyTypeBottomSheet

@Composable
fun HomeDialog(
    isShowPartyTypeBottomSheet: Boolean,
    selectedPartyTypeList: List<String>,
    onClickPartyType: (String) -> Unit,
    onCloseBottomSheet: () -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
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
}