package com.party.guham2.presentation.screens.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.party.guham2.design.BLACK
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.navigation.BottomNavigationBar
import com.party.guham2.navigation.Screens
import com.party.guham2.navigation.fromBottomRoute
import com.party.guham2.presentation.PresentationConstants.ANIMATION_DURATION
import com.party.guham2.presentation.screens.active.ActiveScreenRoute
import com.party.guham2.presentation.screens.home.HomeScreenRoute
import com.party.guham2.presentation.screens.home.component.CreatePartyFloatingButton
import com.party.guham2.presentation.screens.home.component.FloatingSection
import com.party.guham2.presentation.screens.home.component.NavigateUpFloatingButton
import com.party.guham2.presentation.screens.profile.ProfileScreenRoute
import kotlinx.coroutines.launch

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value.fromBottomRoute()

    // 블러효과 뷰가 보이는지
    var isShowBlurView by remember { mutableStateOf(false) }
    var showParty by remember { mutableStateOf(false) }
    var selectedHomeTab by remember { mutableStateOf(homeTopTabList[0]) }
    val selectedIndex = homeTopTabList.indexOf(selectedHomeTab)

    val scope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()

    // 파티, 모집공고 탭에서 스크롤이 되고 있는이 여부
    val isScrollPartyTab by remember { derivedStateOf { gridState.firstVisibleItemIndex > 0}}
    val isScrollRecruitmentTab by remember { derivedStateOf { listState.firstVisibleItemIndex > 0}}

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    radiusX = if(isShowBlurView) 10.dp else 0.dp,
                    radiusY = if(isShowBlurView) 10.dp else 0.dp,
                )
            ,
            bottomBar = {
                BottomNavigationBar(
                    currentScreen = currentScreen,
                    navController = navController,
                    onTabClick = { bottomBarScreen ->
                        navController.navigate(bottomBarScreen.screen){
                            popUpTo(navController.graph.findStartDestination().route!!){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            },
        ){ paddingValues ->
            BottomBarGraph(
                navController = navController,
                paddingValues = paddingValues,
                selectedHomeTab = {
                    selectedHomeTab = it
                },
                gridState = gridState,
                listState = listState
            )
        }

        if(isShowBlurView){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BLACK.copy(alpha = 0.7f))
                    .noRippleClickable {
                        isShowBlurView = false
                        showParty = false
                    }
                    .zIndex(0f)
            )
        }

        val shouldShowFab =
            when (currentScreen.screen) {
                Screens.Active -> true
                Screens.Home   -> selectedIndex in 0..1
                else           -> false
            }

        FloatingSection(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 92.dp, end = 20.dp)
                .zIndex(1f)
            ,
            showParty = showParty,
            createPartyFloating = {
                if (shouldShowFab) {
                    CreatePartyFloatingButton(
                        modifier = Modifier.zIndex(1f),
                        isShowBlurView = isShowBlurView,
                        onClick = {
                            isShowBlurView = !isShowBlurView
                            showParty = !showParty
                        }
                    )
                }

            },
            navigateUpFloating = {
                if (currentScreen.screen == Screens.Home && selectedIndex in 1..2) {
                    if(isScrollPartyTab || isScrollRecruitmentTab){
                        NavigateUpFloatingButton(
                            onClick = {
                                scope.launch {
                                    when {
                                        isScrollPartyTab -> gridState.animateScrollToItem(0)
                                        isScrollRecruitmentTab -> listState.animateScrollToItem(0)
                                    }
                                }
                            }
                        )
                    }
                }
            },
            onGotoCreateParty = {}
        )
    }
}

@Composable
private fun BottomBarGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    selectedHomeTab: (String) -> Unit,
    gridState: LazyGridState,
    listState: LazyListState,
){
    NavHost(
        modifier = Modifier
            .background(WHITE)
            .padding(paddingValues)
        ,
        navController = navController,
        startDestination = Screens.Main,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
    ){
        navigation<Screens.Main>(
            startDestination = Screens.Home
        ){
            composable<Screens.Home> {
                HomeScreenRoute(
                    navController = navController,
                    selectedHomeTab = selectedHomeTab,
                    gridState = gridState,
                    listState = listState,
                )
            }
            composable<Screens.Active> {
                ActiveScreenRoute()
            }
            composable<Screens.Profile> {
                ProfileScreenRoute()
            }
        }
    }
}