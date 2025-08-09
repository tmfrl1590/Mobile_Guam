package com.party.guham2.presentation.screens.home.tab_lounge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.screens.home.component.NewRecruitmentArea
import com.party.guham2.presentation.screens.home.state.HomeState

@Composable
fun LoungeArea(
    homeState: HomeState,
){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        BannerArea(bannerList = homeState.bannerList)

        HeightSpacer(heightDp = 40.dp)

        NewRecruitmentArea(
            homeState = homeState,
            onClick = {_, _ ->},
            onGoRecruitment = {}
        )
    }
}

