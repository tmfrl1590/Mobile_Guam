package com.party.guham2.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.party.guham2.design.WHITE
import com.party.guham2.navigation.MainTab
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.PresentationConstants.ANIMATION_DURATION
import com.party.guham2.presentation.screens.guide_permission.GuidePermissionScreenRoute
import com.party.guham2.presentation.screens.join.joinGraph
import com.party.guham2.presentation.screens.login.LoginScreenRoute
import com.party.guham2.presentation.screens.main.MainScreen
import com.party.guham2.presentation.screens.recruitment_detail.RecruitmentDetailScreenRoute
import com.party.guham2.presentation.screens.splash.SplashScreenRoute

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    NavHost(
        navController = navController,
        startDestination = Screens.Splash,
        modifier = Modifier
            .fillMaxSize()
            .background(WHITE)
        ,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
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
        composable<Screens.Main> { backStackEntry ->
            val tabName = backStackEntry.toRoute<Screens.Main>().tabName
            MainScreen(
                tabName = tabName,
                onClickRecruitmentCard = { partyRecruitmentId, partyId ->
                    navController.navigate(Screens.RecruitmentDetail(partyRecruitmentId = partyRecruitmentId))
                }
            )
        }
        composable<Screens.RecruitmentDetail> { backStackEntry ->
            val partyRecruitmentId = backStackEntry.toRoute<Screens.RecruitmentDetail>().partyRecruitmentId
            RecruitmentDetailScreenRoute(
                navController = navController,
                partyRecruitmentId = partyRecruitmentId,
                onTabClick = { selectedMainTab ->
                    navController.navigate(route = Screens.Main(tabName = selectedMainTab.name) ){
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}