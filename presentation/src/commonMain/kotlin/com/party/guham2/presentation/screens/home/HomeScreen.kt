package com.party.guham2.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.screens.app.AppState
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
import com.party.guham2.presentation.screens.home.viewmodel.HomeEvent
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    state: AppState,
    homeTopTabList: List<String>,
    isFirstVersionCheck: Boolean,
    homeViewModel: HomeViewModel = koinViewModel(),
    onChangeFirstVersionCheck: () -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    /*onGotoSearch: () -> Unit,
    onGotoNotification: () -> Unit,
    onClickBanner: (String) -> Unit,

    onGotoPartyDetail: (Int) -> Unit,
    onGotoDetailProfile: () -> Unit,*/
    onTabClick: (String) -> Unit,
    onStartScrollParty: (Boolean) -> Unit,
    onStartScrollRecruitment: (Boolean) -> Unit
) {
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val partyState by homeViewModel.partyState.collectAsStateWithLifecycle()
    val recruitmentState by homeViewModel.recruitmentState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()

    val isFabVisibleParty by remember { derivedStateOf { gridState.firstVisibleItemIndex > 0}}
    val isFabVisibleRecruitment by remember { derivedStateOf { listState.firstVisibleItemIndex > 0}}
    onStartScrollParty(isFabVisibleParty)
    onStartScrollRecruitment(isFabVisibleRecruitment)

    LaunchedEffect(Unit) {
        HomeEvent.scrollToUpParty.collectLatest {
            gridState.animateScrollToItem(0)
        }
    }
    LaunchedEffect(key1 = Unit) {
        HomeEvent.scrollToUpRecruitment.collectLatest {
            listState.animateScrollToItem(0)
        }
    }

    LaunchedEffect(key1 = recruitmentState.selectedMainPosition){
        homeViewModel.getPositionList(main = recruitmentState.selectedMainPosition)
    }

    HomeDialog(
        isShowPartyTypeBottomSheet = partyState.isShowPartyTypeBottomSheet || recruitmentState.isShowPartyTypeBottomSheet,
        selectedPartyTypeList = if(state.selectedTabText == homeTopTabList[1]) partyState.selectedPartyTypeList else recruitmentState.selectedPartyTypeList,
        onCloseBottomSheet = {
            if(state.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnShowPartyTypeBottomSheet(isShow = false))
            } else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnShowPartyTypeBottomSheet(isShow = false))
            }
        },
        onClickPartyType = {
            if(state.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnSelectPartyType(partyType = it))
            }else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnSelectPartyType(partyType = it))
            }
        },
        onReset = {
            if(state.selectedTabText == homeTopTabList[1]){
                homeViewModel.onPartyAction(action = HomeAction.OnResetPartyType)
            } else {
                homeViewModel.onRecruitmentAction(action = HomeRecruitmentAction.OnResetPartyType)
            }
        },
        onApply = {
            if(state.selectedTabText == homeTopTabList[1]){
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
        state = state,
        gridState = gridState,
        listState = listState,
        homeState = homeState,
        partyState = partyState,
        recruitmentState = recruitmentState,
        onGoToSearch = {},
        onGoToAlarm = {},
        onAction = { action -> homeViewModel.onPartyAction(action = action) },
        onRecruitmentAction = { action -> homeViewModel.onRecruitmentAction(action) },
        onClickRecruitmentCard = onClickRecruitmentCard,
        onTabClick = onTabClick
    )
}

@Composable
private fun HomeScreen(
    state: AppState,
    gridState: LazyGridState,
    listState: LazyListState,
    homeState: HomeState,
    partyState: PartyState,
    recruitmentState: RecruitmentState,
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
    onAction: (HomeAction) -> Unit,
    onRecruitmentAction: (HomeRecruitmentAction) -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    onTabClick: (String) -> Unit,
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
                selectedTabText = state.selectedTabText,
                onClickTab = onTabClick
            )

            when (state.selectedTabText) {
                homeTopTabList[0] -> {
                    LoungeSection(
                        bannerList = homeState.bannerList,
                        partyList = partyState.partyList,
                        recruitmentList = recruitmentState.recruitmentList,
                        onGotoPartyTab = { onTabClick(homeTopTabList[2]) },
                        onGoRecruitmentTab = { onTabClick(homeTopTabList[3])},
                        onClickPartyCard = {},
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
                        onClickPartyCard = {}
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