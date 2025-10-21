package com.party.guham2.presentation.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.party.guham2.design.component.tab_area.homeTopTabList
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.active.ActiveScreenRoute
import com.party.guham2.presentation.screens.app.AppState
import com.party.guham2.presentation.screens.home.HomeScreenRoute
import com.party.guham2.presentation.screens.profile.ProfileScreenRoute

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    state: AppState,
    //onGotoSearch: () -> Unit,
    //onGotoNotification: () -> Unit,
    //onClickBanner: (String) -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    //onGotoPartyDetail: (Int) -> Unit,
    //onGotoDetailProfile: () -> Unit,
    //onGoSetting: () -> Unit,
    //onGotoProfileEdit: () -> Unit,
    isFirstVersionCheck: Boolean,
    onChangeFirstVersionCheck: (Boolean) -> Unit,
    onTabClick: (String) -> Unit,
    onStartScrollParty: (Boolean) -> Unit,
    onStartScrollRecruitment: (Boolean) -> Unit,
    onStartScroll: (Boolean) -> Unit,
) {
    composable<Screens.Home> {
        HomeScreenRoute(
            state = state,
            homeTopTabList = homeTopTabList,
            isFirstVersionCheck = isFirstVersionCheck,
            onChangeFirstVersionCheck = { onChangeFirstVersionCheck(false)},
           /* onGotoSearch = onGotoSearch,
            onGotoNotification = onGotoNotification,
            onClickBanner = onClickBanner,
            onGotoPartyDetail = onGotoPartyDetail,
            onGotoDetailProfile = onGotoDetailProfile,*/
            onTabClick = onTabClick,
            onStartScrollParty = onStartScrollParty,
            onStartScrollRecruitment = onStartScrollRecruitment,
            onClickRecruitmentCard = onClickRecruitmentCard
        )
    }
    composable<Screens.State> {
        ActiveScreenRoute(
            onStartScroll = onStartScroll,
            onGotoPartyDetail = {},
            onGoToSearch = {},
            onGotoNotification = {},
        )
    }
    composable<Screens.Profile> {
        ProfileScreenRoute(

        )
    }
}