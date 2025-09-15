package com.party.guham2.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.action.HomeRecruitmentAction
import com.party.guham2.presentation.screens.home.component.HomeDialog
import com.party.guham2.presentation.screens.home.component.HomeTabBarSection
import com.party.guham2.presentation.screens.home.component.HomeTopBar
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.presentation.screens.home.state.PartyState
import com.party.guham2.presentation.screens.home.state.RecruitmentState
import com.party.guham2.presentation.screens.home.tab_lounge.LoungeSection
import com.party.guham2.presentation.screens.home.tab_lounge.PartySection
import com.party.guham2.presentation.screens.home.tab_lounge.RecruitmentSection
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    navController: NavHostController,
    selectedHomeTab: (String) -> Unit,
    gridState: LazyGridState,
    listState: LazyListState,
    onClickPartyCard: (Int) -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val partyState by homeViewModel.partyState.collectAsStateWithLifecycle()
    val recruitmentState by homeViewModel.recruitmentState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = recruitmentState.selectedMainPosition){
        homeViewModel.getPositionList(main = recruitmentState.selectedMainPosition)
    }

    LaunchedEffect(key1 = homeState.selectedTabText){
        selectedHomeTab(homeState.selectedTabText)
    }

    HomeDialog(
        isShowPartyTypeBottomSheet = partyState.isShowPartyTypeBottomSheet || recruitmentState.isShowPartyTypeBottomSheet,
        selectedPartyTypeList = if(homeState.selectedTabText == homeTopTabList[1]) partyState.selectedPartyTypeList else recruitmentState.selectedPartyTypeList,
        onCloseBottomSheet = {
            if(homeState.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnShowPartyTypeBottomSheet(isShow = false))
            } else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnShowPartyTypeBottomSheet(isShow = false))
            }
        },
        onClickPartyType = {
            if(homeState.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnSelectPartyType(partyType = it))
            }else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnSelectPartyType(partyType = it))
            }
        },
        onReset = {
            if(homeState.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnResetPartyType)
            } else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnResetPartyType)
            }
        },
        onApply = {
            if(homeState.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnApplyPartyType)
            } else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnApplyPartyType)
            }
        },
        isShowPositionBottomSheet = recruitmentState.isShowPositionBottomSheet,
        selectedMainPosition = recruitmentState.selectedMainPosition,
        onClickMainPosition = { homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnSelectMainPosition(mainPosition = it)) },
        onClickSubPosition = { selectedSubPosition -> homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnSelectSubPosition(subPosition = selectedSubPosition))},
        onClosePositionBottomSheet = { homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnShowPositionBottomSheet(isShow = false))},
        subPositionList = recruitmentState.mainAndSubPositionList,
        selectedSubPositionList = recruitmentState.selectedSubPositionList.map { it.sub to it.id },
        selectedMainAndSubPositionList = recruitmentState.selectedMainAndSubPosition,
        onDelete = { homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnDeleteSelectedMainAndSubPosition(position = it)) },
        onResetSelectedPosition = { homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnResetSelectedMainAndSubPosition)},
        onApplySelectedPosition = { homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnApplyMainAndSubPosition)}
    )

    HomeScreen(
        gridState = gridState,
        listState = listState,
        homeState = homeState,
        partyState = partyState,
        recruitmentState = recruitmentState,
        onGoToSearch = {},
        onGoToAlarm = {},
        onAction = { action -> homeViewModel.onPartyAction(action = action) },
        onRecruitmentAction = { action -> homeViewModel.onRecruitmentAction(action) },
        onClickPartyCard = onClickPartyCard,
        onClickRecruitmentCard = onClickRecruitmentCard
    )
}

@Composable
private fun HomeScreen(
    gridState: LazyGridState,
    listState: LazyListState,
    homeState: HomeState,
    partyState: PartyState,
    recruitmentState: RecruitmentState,
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
    onAction: (HomeAction) -> Unit,
    onRecruitmentAction: (HomeRecruitmentAction) -> Unit,
    onClickPartyCard: (Int) -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(horizontal = 20.dp)
        ) {
            HomeTopBar(
                onGoToSearch = onGoToSearch,
                onGoToAlarm = onGoToAlarm,
            )
            HomeTabBarSection(
                homeTopTabList = homeTopTabList,
                selectedTabText = homeState.selectedTabText,
                onClickTab = { selectedTabText -> onAction(HomeAction.OnClickTab(tabText = selectedTabText)) }
            )

            when (homeState.selectedTabText) {
                homeTopTabList[0] -> {
                    LoungeSection(
                        bannerList = homeState.bannerList,
                        partyList = partyState.partyList,
                        recruitmentList = recruitmentState.recruitmentList,
                        onGotoPartyTab = { onAction(HomeAction.OnClickTab(tabText = homeTopTabList[1])) },
                        onGoRecruitmentTab = {onAction(HomeAction.OnClickTab(tabText = homeTopTabList[2]))},
                        onClickPartyCard = onClickPartyCard,
                        onClickRecruitmentCard = onClickRecruitmentCard
                    )
                }

                homeTopTabList[1] -> {
                    PartySection(
                        gridState = gridState,
                        partyState = partyState,
                        onClickChip = { onAction(HomeAction.OnShowPartyTypeBottomSheet(isShow = true)) },
                        selectedPartyTypeCount = partyState.selectedPartyTypeCount,
                        onToggle = { onAction(HomeAction.OnTogglePartySection(isActive = it))},
                        onChangeOrderByPartySection = { onAction(HomeAction.OnDescPartySection(isDesc = it))},
                        onClickPartyCard = onClickPartyCard
                    )
                }

                homeTopTabList[2] -> {
                    RecruitmentSection(
                        listState = listState,
                        recruitmentState = recruitmentState,
                        onClickRecruitmentChip = { onRecruitmentAction(HomeRecruitmentAction.OnShowPositionBottomSheet(isShow = true))},
                        onClickChip = { onRecruitmentAction(HomeRecruitmentAction.OnShowPartyTypeBottomSheet(isShow = true))},
                        onChangeOrderByPartySection = { onRecruitmentAction(HomeRecruitmentAction.OnDescPartySection(isDesc = it)) },
                        onClickRecruitmentCard = onClickRecruitmentCard
                    )
                }
            }
        }
    }
}