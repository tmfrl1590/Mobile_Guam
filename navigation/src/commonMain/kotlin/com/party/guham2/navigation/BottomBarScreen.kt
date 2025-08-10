package com.party.guham2.navigation

import com.party.guham2.design.DesignResources
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomBarScreen(
    val screen: Screens,
    val name: String,
    val icon: DrawableResource,
){
    data object Home: BottomBarScreen(
        screen = Screens.Home,
        name = "홈",
        icon = DesignResources.Icon.icon_selected_home,
    )

    data object Active: BottomBarScreen(
        screen = Screens.Active,
        name = "활동",
        icon = DesignResources.Icon.icon_selected_active,
    )

    data object Profile: BottomBarScreen(
        screen = Screens.Profile,
        name = "프로필",
        icon = DesignResources.Icon.icon_selected_profile,
    )
}

val bottomDestinations = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Active,
    BottomBarScreen.Profile
)