package com.party.guham2.presentation.screens.party_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.component.tab_area.TabSection
import com.party.guham2.design.component.util.HeightSpacer

@Composable
fun PartyDetailTabSection(
    partyDetailTabList: List<String>,
    selectedTabText: String,
    onClickTab: (String) -> Unit,
){
    Column{
        HeightSpacer(8.dp)
        TabSection(
            modifier = Modifier.padding(start = 20.dp),
            tabList = partyDetailTabList,
            selectedTabText = selectedTabText,
            onClickTab = onClickTab
        )
    }

}