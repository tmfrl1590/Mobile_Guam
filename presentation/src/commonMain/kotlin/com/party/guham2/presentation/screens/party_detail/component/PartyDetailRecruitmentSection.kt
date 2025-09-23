package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.DARK100
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY500
import com.party.guham2.design.component.chip.NoBorderBottomSheetChip
import com.party.guham2.design.component.chip.OrderByCreateDtChip
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.presentation.model.party.PartyRecruitmentModel
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PartyDetailRecruitmentSection(
    state: PartyDetailState,
    onChangeProgress: (Boolean) -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
){
    PartyDetailRecruitmentContent(
        state = state,
        onChangeProgress = onChangeProgress,
        onReset = onReset,
        onApply = onApply
    )
}

@Composable
fun PartyDetailRecruitmentContent(
    state: PartyDetailState,
    onChangeProgress: (Boolean) -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        PartyDetailTitle(
            title = "모집공고",
            number = "${state.partyRecruitmentList.size}",
            progressContent = {
                ChangeProgress(
                    isProgress = state.isOnToggle,
                    onChangeProgress = onChangeProgress
                )
            }
        )
        HeightSpacer(heightDp = 16.dp)
        PartyDetailRecruitmentFilterArea(
            state = state,
            selectedCreateDataOrderByDesc = false,
            onChangeSelected = {},
            onShowPositionFilter = {},
            onPositionClick = {},
            onReset = onReset,
            onApply = onApply,
        )
    }
}

@Composable
private fun ChangeProgress(
    isProgress: Boolean,
    onChangeProgress: (Boolean) -> Unit,
) {
    Row {
        CustomText(
            text = "진행중",
            fontSize = B2,
            color = if(isProgress) DARK100 else GRAY500,
            fontWeight = FontWeight.SemiBold,
        )
        WidthSpacer(widthDp = 2.dp)
        Image(
            painter = if(isProgress) painterResource(DesignResources.Icon.icon_toggle_on) else painterResource(DesignResources.Icon.icon_toggle_off),
            contentDescription = "toggle",
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable { onChangeProgress(!isProgress) }
        )
    }
}

@Composable
fun PartyDetailRecruitmentFilterArea(
    state: PartyDetailState,
    selectedCreateDataOrderByDesc: Boolean,
    onChangeSelected: (Boolean) -> Unit,
    onShowPositionFilter: (Boolean) -> Unit,
    onPositionClick: (String) -> Unit,
    onReset: () -> Unit,
    onApply: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NoBorderBottomSheetChip(
            modifier = Modifier,
            chipName = state.selectedPosition,
            isSheetOpen = false,
            onClick = { onShowPositionFilter(!state.isShowPositionFilter) },
            spacer = 0.dp,
            painter = painterResource(resource = DesignResources.Icon.icon_arrow_up_long),
        )

        OrderByCreateDtChip(
            text = "등록일순",
            orderByDesc = selectedCreateDataOrderByDesc,
            onChangeSelected = { onChangeSelected(it) }
        )
    }
}