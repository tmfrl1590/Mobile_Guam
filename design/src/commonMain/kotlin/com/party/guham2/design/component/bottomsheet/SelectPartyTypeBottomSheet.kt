package com.party.guham2.design.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.party.guham2.design.component.bottomsheet.component.BottomSheetTitleSection
import com.party.guham2.design.component.bottomsheet.component.ResetAndApplySection
import com.party.guham2.design.component.bottomsheet.component.contents.BottomSheetPartyTypeSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPartyTypeBottomSheet(
    title: String,
    selectedPartyTypeList: List<String>,
    onClickPartyType: (String) -> Unit,
    onCloseBottomSheet: () -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onCloseBottomSheet()
        },
        containerColor = Color.White,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp)
        ) {
            BottomSheetTitleSection(
                title = title,
                onCloseBottomSheet = onCloseBottomSheet
            )

            BottomSheetPartyTypeSection(
                selectedPartyTypeList = selectedPartyTypeList,
                onClickPartyType = onClickPartyType
            )

            ResetAndApplySection(
                onReset = onReset,
                onApply = onApply
            )
        }
    }
}