package com.party.guham2.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.component.HomeTabBarArea
import com.party.guham2.presentation.screens.home.component.HomeTopBar
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.presentation.screens.home.tab_lounge.LoungeArea
import com.party.guham2.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by homeViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        homeState = homeState,
        onGoToSearch = {},
        onGoToAlarm = {},
        onAction = { action ->
            homeViewModel.onAction(action = action)
        }
    )
}

@Composable
private fun HomeScreen(
    homeState: HomeState,
    onGoToSearch: () -> Unit,
    onGoToAlarm: () -> Unit,
    onAction: (HomeAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {

            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WHITE)
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                HomeTopBar(
                    onGoToSearch = onGoToSearch,
                    onGoToAlarm = onGoToAlarm,
                )
                HomeTabBarArea(
                    homeTopTabList = homeTopTabList,
                    selectedTabText = homeState.selectedTabText,
                    onClickTab = { selectedTabText -> onAction(HomeAction.OnClickTab(tabText = selectedTabText)) }
                )

                when (homeState.selectedTabText) {
                    homeTopTabList[0] -> {
                        LoungeArea(
                            homeState = homeState,
                        )
                    }

                    homeTopTabList[1] -> {

                    }

                    homeTopTabList[2] -> {
                    }
                }
            }
        }
    }
}