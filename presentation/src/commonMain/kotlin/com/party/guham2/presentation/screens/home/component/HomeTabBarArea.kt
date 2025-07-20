package com.party.guham2.presentation.screens.home.component

import androidx.compose.runtime.Composable
import com.party.guham2.design.component.tab_area.TabArea

@Composable
fun HomeTabBarArea(
    homeTopTabList: List<String>,
    selectedTabText: String,
    onClickTab: (String) -> Unit,
){
    TabArea(
        tabList = homeTopTabList,
        selectedTabText = selectedTabText,
        onClickTab = onClickTab
    )
}