package com.party.guham2.presentation.screens.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.party.guham2.design.WHITE
import com.party.guham2.navigation.BottomNavigationBar
import com.party.guham2.navigation.Screens
import com.party.guham2.navigation.getCurrentScreen
import com.party.guham2.presentation.PresentationConstants.ANIMATION_DURATION
import com.party.guham2.presentation.screens.app.AppState
import com.party.guham2.presentation.screens.recruitment_detail.RecruitmentDetailScreenRoute

@Composable
fun MainScreen(
    snackBarHostState: SnackbarHostState,
    state: AppState,
    isFirstVersionCheck: Boolean,
    onChangeFirstVersionCheck: (Boolean) -> Unit,
    onGotoLogin: () -> Unit,
    onTabClick: (String) -> Unit,
    onCurrentScreen: (String?) -> Unit,
    onStartScrollParty: (Boolean) -> Unit,
    onStartScrollRecruitment: (Boolean) -> Unit,
    onStartScroll: (Boolean) -> Unit,
){
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = getCurrentScreen(navController)

    LaunchedEffect(key1 = currentScreen) {
        onCurrentScreen(currentScreen)
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WHITE)
    ){
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
            ,
            bottomBar = {
                BottomNavigationBar(
                    currentScreen = currentScreen ?: "",
                    navController = navController,
                    onTabClick = { screen ->
                        navController.navigate(screen){
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
                snackBarHostState = snackBarHostState,
                state = state,
                isFirstVersionCheck = isFirstVersionCheck,
                onChangeFirstVersionCheck = onChangeFirstVersionCheck,
                navController = navController,
                paddingValues = paddingValues,
                /*onGotoSearch = { navController.navigate(Screens.Search)},
                onGotoNotification = { navController.navigate(Screens.Notification)},
                onClickBanner = { navController.navigate(route = Screens.WebView(webViewUrl = it))},
                onGotoPartyDetail = { navController.navigate(Screens.PartyDetail(it))},
                onGotoDetailProfile = { navController.navigate(route = Screens.HomeDetailProfile)},
                onGoSetting = { navController.navigate(route = Screens.ManageAuth) },
                onGotoProfileEdit = { navController.navigate(route = Screens.ProfileEdit)},*/
                onGotoLogin = onGotoLogin,
                onTabClick = onTabClick,
                onStartScrollParty = onStartScrollParty,
                onStartScrollRecruitment = onStartScrollRecruitment,
                onStartScroll = onStartScroll,
                onClickRecruitmentCard = { partyId, partyRecruitmentId -> navController.navigate(Screens.RecruitmentDetail(partyId = partyId, partyRecruitmentId = partyRecruitmentId)) },
            )
        }

        /*if(isShowBlurView){
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
            when (currentMainTab.screen) {
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
                if (currentMainTab.screen == Screens.Home && selectedIndex in 1..2) {
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
        )*/
    }
}

@Composable
private fun BottomBarGraph(
    snackBarHostState: SnackbarHostState,
    state: AppState,
    isFirstVersionCheck: Boolean,
    onChangeFirstVersionCheck: (Boolean) -> Unit,
    navController: NavHostController,
    paddingValues: PaddingValues,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    /*onGotoSearch: () -> Unit,
    onGotoNotification: () -> Unit,
    onClickBanner: (String) -> Unit,
    onGotoPartyDetail: (Int) -> Unit,
    onGotoDetailProfile: () -> Unit,
    onGoSetting: () -> Unit,
    onGotoProfileEdit: () -> Unit,*/
    onGotoLogin: () -> Unit,
    onTabClick: (String) -> Unit,
    onStartScrollParty: (Boolean) -> Unit,
    onStartScrollRecruitment: (Boolean) -> Unit,
    onStartScroll: (Boolean) -> Unit,
){
    LaunchedEffect(key1 = Unit) {
        //MainEvent.goToPartyCreate.collect { navController.navigate(route = Screens.PartyCreate) }
    }

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
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        }
    ){
        navigation<Screens.Main>(
            startDestination = Screens.Home,
        ){
            mainGraph(
                navController = navController,
                state = state,
                isFirstVersionCheck = isFirstVersionCheck,
                onChangeFirstVersionCheck = onChangeFirstVersionCheck,
                /*onGotoProfileEdit = onGotoProfileEdit,
                onGotoPartyDetail = onGotoPartyDetail,
                onGotoSearch = onGotoSearch,
                onGotoNotification = onGotoNotification,
                onGoSetting = onGoSetting,
                onGotoDetailProfile = onGotoDetailProfile,
                onClickBanner = onClickBanner,*/
                onTabClick = onTabClick,
                onStartScrollParty = onStartScrollParty,
                onStartScrollRecruitment = onStartScrollRecruitment,
                onStartScroll = onStartScroll,
                onClickRecruitmentCard = onClickRecruitmentCard,
            )

            composable<Screens.RecruitmentDetail> { backStackEntry ->
                val partyId = backStackEntry.toRoute<Screens.RecruitmentDetail>().partyId
                val partyRecruitmentId = backStackEntry.toRoute<Screens.RecruitmentDetail>().partyRecruitmentId
                RecruitmentDetailScreenRoute(
                    navController = navController,
                    partyId = partyId,
                    partyRecruitmentId = partyRecruitmentId,
                )
            }
        }
    }
}