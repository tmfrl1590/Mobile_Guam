package com.party.guham2.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.DesignResources
import com.party.guham2.design.WHITE
import com.party.guham2.navigation.MainTab
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreenRoute(
    navController: NavHostController,
    splashViewModel: SplashViewModel = koinViewModel()
){
    val splashState by splashViewModel.splashState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        delay(2000L)
        val targetScreen = if (splashState.accessToken == null) Screens.GuidePermission else Screens.Main(tabName = MainTab.Home.name)
        navController.navigate(route = targetScreen) {
            popUpTo(Screens.Splash) { inclusive = true }
        }
    }
    SplashScreen()
}

@Composable
private fun SplashScreen(){
    Image(
        painter = painterResource(resource = DesignResources.Image.image_splash),
        contentDescription = "splash",
        modifier = Modifier
            .background(WHITE)
    )
}