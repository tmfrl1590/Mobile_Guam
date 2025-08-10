package com.party.guham2.presentation.screens.home.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.component.tab_area.TabSection

@Composable
fun HomeTabBarSection(
    homeTopTabList: List<String>,
    selectedTabText: String,
    onClickTab: (String) -> Unit,
){
    TabSection(
        tabList = homeTopTabList,
        selectedTabText = selectedTabText,
        onClickTab = onClickTab
    )
}