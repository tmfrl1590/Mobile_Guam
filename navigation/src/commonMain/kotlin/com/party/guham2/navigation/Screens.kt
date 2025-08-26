package com.party.guham2.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Splash: Screens
    @Serializable
    data object GuidePermission: Screens
    @Serializable
    data object Login: Screens
    @Serializable
    data object Main: Screens
    @Serializable
    data object Home: Screens
    @Serializable
    data object Active: Screens
    @Serializable
    data object Profile: Screens
}