package com.party.guham2.presentation.screens.join

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.join.screen.JoinEmailScreenRoute
import com.party.guham2.presentation.screens.join.screen.JoinNickNameScreenRoute
import com.party.guham2.presentation.screens.join.viewmodel.JoinViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.joinGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
){
    navigation<Screens.Join>(
        startDestination = Screens.JoinEmail
    ){
        composable<Screens.JoinEmail> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry<Screens.Join>()
            }

            val joinViewModel: JoinViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

            JoinEmailScreenRoute(
                navController = navController,
                joinViewModel = joinViewModel
            )
        }
        composable<Screens.JoinNickname> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry<Screens.Join>()
            }

            val joinViewModel: JoinViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

            JoinNickNameScreenRoute(
                navController = navController,
                snackBarHostState = snackBarHostState,
                joinViewModel = joinViewModel
            )
        }
    }
}