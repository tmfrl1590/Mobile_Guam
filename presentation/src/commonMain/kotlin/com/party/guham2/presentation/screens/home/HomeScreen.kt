package com.party.guham2.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.component.HomeDialog
import com.party.guham2.presentation.screens.home.component.HomeTabBarSection
import com.party.guham2.presentation.screens.home.component.HomeTopBar
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.presentation.screens.home.state.PartyState
import com.party.guham2.presentation.screens.home.state.RecruitmentState
import com.party.guham2.presentation.screens.home.tab_lounge.LoungeSection
import com.party.guham2.presentation.screens.home.tab_lounge.PartySection
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val partyState by homeViewModel.partyState.collectAsStateWithLifecycle()
    val recruitmentState by homeViewModel.recruitmentState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()

    HomeDialog(
        isShowPartyTypeBottomSheet = partyState.isShowPartyTypeBottomSheet,
        selectedPartyTypeList = partyState.selectedPartyTypeList,
        onCloseBottomSheet = { homeViewModel.onAction(action = HomeAction.OnShowPartyTypeBottomSheet(isShow = false))},
        onClickPartyType = { homeViewModel.onAction(action = HomeAction.OnSelectPartyType(partyType = it))},
        onReset = { homeViewModel.onAction(action = HomeAction.OnResetPartyType)},
        onApply = { homeViewModel.onAction(action = HomeAction.OnApplyPartyType)},
    )

    HomeScreen(
        gridState = gridState,
        homeState = homeState,
        partyState = partyState,
        recruitmentState = recruitmentState,
        onGoToSearch = {},
        onGoToAlarm = {},
        onAction = { action ->
            homeViewModel.onAction(action = action)
        }
    )
}

@Composable
private fun HomeScreen(
    gridState: LazyGridState,
    homeState: HomeState,
    partyState: PartyState,
    recruitmentState: RecruitmentState,
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
    onAction: (HomeAction) -> Unit,
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
                        onClickPartyCard = {},
                        onClickRecruitmentCard = {_, _ ->}
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
                        onClickPartyCard = {}
                    )
                }

                homeTopTabList[2] -> {
                }
            }
        }
    }
}