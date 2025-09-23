package com.party.guham2.presentation.screens.party_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.party.guham2.design.WHITE
import com.party.guham2.design.type.OrderDescType
import com.party.guham2.design.type.SortType
import com.party.guham2.design.type.StatusType
import com.party.guham2.navigation.BottomNavigationBar
import com.party.guham2.navigation.MainTab
import com.party.guham2.navigation.Screens
import com.party.guham2.navigation.toMainTab
import com.party.guham2.presentation.screens.party_detail.action.PartyDetailAction
import com.party.guham2.presentation.screens.party_detail.component.PartyDetailSection
import com.party.guham2.presentation.screens.party_detail.component.PartyDetailTitleSection
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState
import com.party.guham2.presentation.screens.party_detail.viewmodel.PartyDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PartyDetailScreenRoute(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    partyId: Int,
    partyDetailViewModel: PartyDetailViewModel = koinViewModel(),
    onTabClick: (MainTab) -> Unit,
){
    val state by partyDetailViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit){
        partyDetailViewModel.getPartyDetail(partyId = partyId)
        partyDetailViewModel.getPartyUsers(partyId = partyId, page = 1, limit = 50, sort = SortType.CREATED_AT.type, order = OrderDescType.DESC.type)
        partyDetailViewModel.getPartyRecruitment(partyId = partyId, sort = SortType.CREATED_AT.type, order = OrderDescType.DESC.type, main = null, status = StatusType.ACTIVE.type)
    }

    PartyDetailScreen(
        navController = navController,
        snackBarHostState = snackBarHostState,
        state = state,
        partyId = partyId,
        onTabClick = onTabClick,
        onAction = { action -> partyDetailViewModel.onAction(action = action) }
    )
}

@Composable
private fun PartyDetailScreen(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    state: PartyDetailState,
    partyId: Int,
    onTabClick: (MainTab) -> Unit,
    onAction: (PartyDetailAction) -> Unit,
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentMainTab = backStackEntry.toMainTab()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        },
        topBar = {
            PartyDetailTitleSection(
                onNavigateBack = {
                    navController.navigate(Screens.Main(tabName = MainTab.Home.name)) {
                        popUpTo(0) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                authority = state.partyAuthority.authority,
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentMainTab = currentMainTab,
                navController = navController,
                onTabClick = onTabClick
            )
        },
    ){
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WHITE)
            ) {
                PartyDetailSection(
                    state = state,
                    onClickTab = { selectedTab -> onAction(PartyDetailAction.OnClickTab(tabText = selectedTab))},
                    onChangeProgress = { isProgress -> onAction(PartyDetailAction.OnChangeProgress(isProgress = isProgress, partyId = partyId)) },
                )
            }
        }
    }
}