package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY100
import com.party.guham2.design.GRAY600
import com.party.guham2.design.NetworkImageLoad
import com.party.guham2.design.T2
import com.party.guham2.design.component.tab_area.partyDetailTabList
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.type.StatusType
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState

@Composable
fun PartyDetailSection(
    state: PartyDetailState,
    onClickTab: (String) -> Unit,
    onChangeProgress: (Boolean) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            PartyDetailImageSection(
                image = state.partyDetail.image
            )
            HeightSpacer(heightDp = 28.dp)
        }

        item {
            PartyDetailCategorySection(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                tag = StatusType.fromType(state.partyDetail.status).toDisplayText(),
                partyType = state.partyDetail.partyType.type,
                statusContainerColor = StatusType.fromType(state.partyDetail.status).toContainerColor(),
                statusContentColor = StatusType.fromType(state.partyDetail.status).toContentColor(),
            )
            HeightSpacer(heightDp = 12.dp)
        }

        item {
            PartyDetailTitle(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                title = state.partyDetail.title,
            )
            HeightSpacer(heightDp = 32.dp)

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = GRAY100,
                thickness = 12.dp,
            )
        }

        item {
            PartyDetailTabSection(
                partyDetailTabList = partyDetailTabList,
                selectedTabText = state.selectedTabText,
                onClickTab = {onClickTab(it)}
            )
            HeightSpacer(heightDp = 24.dp)
        }

        item {
            when (state.selectedTabText) {
                partyDetailTabList[0] -> PartyDetailDescriptionSection(modifier = Modifier.padding(horizontal = 20.dp), content = state.partyDetail.content)
                partyDetailTabList[1] -> PartyDetailUserSection(state = state, onReports = {})
                partyDetailTabList[2] ->
                    PartyDetailRecruitmentSection(
                        state = state,
                        onChangeProgress = onChangeProgress,
                        onReset = {},
                        onApply = {}
                    )
            }
        }
    }
}

@Composable
private fun PartyDetailImageSection(
    image: String?
){
    NetworkImageLoad(
        modifier = Modifier
            .fillMaxWidth()
            .height(281.dp)
            .aspectRatio(4f / 3f),
        url = image,
    )
}

@Composable
private fun PartyDetailDescriptionSection(
    modifier: Modifier,
    content: String,
){
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = 60.dp)
    ) {
        CustomText(
            text = "파티 소개",
            fontSize = T2,
            color = BLACK,
            fontWeight = FontWeight.Bold
        )
        HeightSpacer(heightDp = 16.dp)
        CustomText(
            text = content,
            fontSize = B1,
            color = GRAY600,
        )
    }
}
