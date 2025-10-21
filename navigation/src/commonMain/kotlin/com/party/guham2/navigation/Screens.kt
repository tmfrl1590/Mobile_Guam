package com.party.guham2.navigation

import com.party.guham2.design.DesignResources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

sealed interface Screens {
    @Serializable
    data object Splash: Screens
    @Serializable
    data object GuidePermission: Screens
    @Serializable
    data object Login: Screens
    @Serializable
    data class Join(
        val email: String,
        val signupAccessToken: String
    ): Screens
    @Serializable
    data object JoinEmail: Screens
    @Serializable
    data object JoinNickname: Screens
    @Serializable
    data object JoinBirthDay: Screens
    @Serializable
    data object JoinGender: Screens
    @Serializable
    data object JoinComplete: Screens
    @Serializable
    data object Main: Screens
    @Serializable
    data object Home: Screens
    @Serializable
    data object State: Screens
    @Serializable
    data object Profile: Screens
    @Serializable
    data class RecruitmentDetail(val partyRecruitmentId: Int, val partyId: Int): Screens
}

sealed class BottomBarScreen (
    val screen: Screens,
    val name: String,
    val icon: DrawableResource,
){
    data object Home: BottomBarScreen(
        screen = Screens.Home,
        name = "홈",
        icon = DesignResources.Icon.icon_selected_home,
    )
    data object State: BottomBarScreen(
        screen = Screens.State,
        name = "활동",
        icon = DesignResources.Icon.icon_selected_active,
    )
    data object Profile: BottomBarScreen(
        screen = Screens.Profile,
        name = "프로필",
        icon = DesignResources.Icon.icon_selected_profile,
    )
}