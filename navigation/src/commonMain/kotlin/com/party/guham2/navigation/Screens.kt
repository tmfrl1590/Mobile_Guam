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
    data class Main(val tabName: String = MainTab.Home.name): Screens
    @Serializable
    data object Home: Screens
    @Serializable
    data object Active: Screens
    @Serializable
    data object Profile: Screens
    @Serializable
    data class RecruitmentDetail(
        val partyId: Int,
        val partyRecruitmentId: Int
    ): Screens
}

@Serializable
enum class MainTab(
    val screen: Screens,
    val tabIcon: DrawableResource,
    val tabName: String,
) {
    Home(
        screen = Screens.Home,
        tabName = "홈",
        tabIcon = DesignResources.Icon.icon_selected_home,
    ),
    Active(
        screen = Screens.Active,
        tabName = "활동",
        tabIcon = DesignResources.Icon.icon_selected_active,
    ),
    Profile(
        screen = Screens.Profile,
        tabName = "프로필",
        tabIcon = DesignResources.Icon.icon_selected_profile,
    )
}