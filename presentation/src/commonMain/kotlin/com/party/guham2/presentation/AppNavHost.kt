package com.party.guham2.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.party.guham2.design.BLACK
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.PresentationConstants.ANIMATION_DURATION
import com.party.guham2.presentation.screens.active.event.ActiveEvent
import com.party.guham2.presentation.screens.app.AppEvent
import com.party.guham2.presentation.screens.app.AppState
import com.party.guham2.presentation.screens.app.AppViewModel
import com.party.guham2.presentation.screens.guide_permission.GuidePermissionScreenRoute
import com.party.guham2.presentation.screens.home.component.CreatePartyFloatingButton
import com.party.guham2.presentation.screens.home.component.FloatingSection
import com.party.guham2.presentation.screens.home.component.NavigateUpFloatingButton
import com.party.guham2.presentation.screens.home.viewmodel.HomeEvent
import com.party.guham2.presentation.screens.join.joinGraph
import com.party.guham2.presentation.screens.login.LoginScreenRoute
import com.party.guham2.presentation.screens.main.MainScreen
import com.party.guham2.presentation.screens.main.event.MainEvent
import com.party.guham2.presentation.screens.splash.SplashScreenRoute
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.get

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    val appViewModel: AppViewModel = koinViewModel()
    val state by appViewModel.state.collectAsStateWithLifecycle()

    // 앱이 처음 실행되었는지를 추적하여 앱 버전 체크를 한 번만 수행하기 위한 플래그
    var isFirstVersionCheck by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
    ){
        NavHost(
            navController = navController,
            startDestination = Screens.Splash,
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .systemBarsPadding()
                .blur(
                    radiusX = if(state.isShowBlackBackground) 10.dp else 0.dp,
                    radiusY = if(state.isShowBlackBackground) 10.dp else 0.dp
                )
            ,
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
            composable<Screens.Splash> {
                SplashScreenRoute(
                    navController = navController,
                )
            }
            composable<Screens.GuidePermission> {
                GuidePermissionScreenRoute(
                    navController = navController,
                )
            }
            composable<Screens.Login> {
                LoginScreenRoute(
                    navController = navController,
                )
            }
            joinGraph(
                navController = navController,
                snackBarHostState = snackBarHostState,
            )
            composable<Screens.Main> {
                MainScreen(
                    snackBarHostState = snackBarHostState,
                    state = state,
                    isFirstVersionCheck = isFirstVersionCheck,
                    onChangeFirstVersionCheck = { isFirstVersionCheck = it },
                    onGotoLogin = {
                        navController.navigate(Screens.Login) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onTabClick = { tab -> appViewModel.selectTab(tab)},
                    onCurrentScreen = { appViewModel.setCurrentScreen(currentScreen = it)},
                    onStartScrollParty = { flag -> appViewModel.startScrollParty(flag) },
                    onStartScrollRecruitment = { flag -> appViewModel.startScrollRecruitment(flag) },
                    onStartScroll = { flag -> appViewModel.startScroll(flag) }
                )
            }
        }

        if(state.isShowBlackBackground){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BLACK.copy(alpha = 0.7f))
                    .noRippleClickable { AppEvent.emit(isShow = false) }
                    .zIndex(0f)
            )
        }

        FloatingSection(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = (getNavigationBarHeight() + 80).dp, end = 20.dp)
                .zIndex(1f),
            isExpandedFloatingButton = state.isShowBlackBackground,
            createPartyFloating = {
                val shouldShowButton = when(state.currentScreen) {
                    "Home" -> state.selectedTabText == homeTopTabList[0] || state.selectedTabText == homeTopTabList[1]
                    "State" -> true
                    else -> false
                }

                if (shouldShowButton) {
                    CreatePartyFloatingButton(
                        isExpandedFloatingButton = state.isShowBlackBackground,
                        onClick = {
                            AppEvent.emit(isShow = !state.isShowBlackBackground)
                        }
                    )
                }
            },
            navigateUpFloating = {
                val shouldShowButton = when(state.currentScreen) {
                    "Home" -> when(state.selectedTabText) {
                        homeTopTabList[1] -> state.isScrollStartParty
                        homeTopTabList[2] -> state.isScrollStartRecruitment
                        else -> false
                    }
                    "State" -> state.isScrollStart
                    else -> false
                }
                NavigateUpFloatingButton(
                    isShowNavigateUpFloatingButton = shouldShowButton,
                    isExpandedFloatingButton = state.isShowBlackBackground,
                    onClick = {handleScrollToTop(state) }
                )
            },
            onGotoCreateParty = {
                MainEvent.gotoPartyCreate()
                AppEvent.emit(isShow = false)
            }
        )
    }

}


private fun handleScrollToTop(state: AppState) {
    when {
        state.currentScreen == "State" -> ActiveEvent.scrollToUp()
        state.selectedTabText == homeTopTabList[1] -> HomeEvent.scrollToUpParty()
        else -> HomeEvent.scrollToUpRecruitment()
    }
}

@Composable
fun getNavigationBarHeight(): Int {
    val density = LocalDensity.current
    val navigationBarsInsets = WindowInsets.navigationBars
    val heightPx = navigationBarsInsets.getBottom(density)
    val heightDp = with(density) { heightPx.toDp() }

    return heightDp.value.toInt()
}