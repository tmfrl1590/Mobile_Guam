package com.party.guham2.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.party.guham2.design.DesignResources
import com.party.guham2.design.WHITE
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
    LaunchedEffect(key1 = Unit){
        delay(1000L)
        navController.navigate(Screens.GuidePermission)
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