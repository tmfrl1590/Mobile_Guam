package com.party.guham2.presentation.screens.active.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.component.tab_area.TabSection

@Composable
fun ActiveTabSection(
    stateTabList: List<String>,
    selectedTabText: String,
    onTabClick: (String) -> Unit,
){
    TabSection(
        tabList = stateTabList,
        selectedTabText = selectedTabText,
        onClickTab = onTabClick,
    )
}