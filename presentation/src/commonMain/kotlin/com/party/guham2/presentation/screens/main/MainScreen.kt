package com.party.guham2.presentation.screens.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.party.guham2.design.WHITE
import com.party.guham2.navigation.BottomNavigationBar
import com.party.guham2.navigation.Screens
import com.party.guham2.navigation.fromBottomRoute
import com.party.guham2.presentation.PresentationConstants.ANIMATION_DURATION
import com.party.guham2.presentation.screens.active.ActiveScreenRoute
import com.party.guham2.presentation.screens.home.HomeScreenRoute
import com.party.guham2.presentation.screens.profile.ProfileScreenRoute

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value.fromBottomRoute()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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
        }
    ){ paddingValues ->
        BottomBarGraph(
            navController = navController,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun BottomBarGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
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
                    navController = navController
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