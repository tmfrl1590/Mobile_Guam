package com.party.guham2.presentation.screens.recruitment_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.party.guham2.design.GRAY100
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.BottomNavigationBar
import com.party.guham2.navigation.MainTab
import com.party.guham2.navigation.toMainTab
import com.party.guham2.presentation.screens.recruitment_detail.component.RecruitmentDescription
import com.party.guham2.presentation.screens.recruitment_detail.component.RecruitmentImageSection
import com.party.guham2.presentation.screens.recruitment_detail.component.RecruitmentInfoSection
import com.party.guham2.presentation.screens.recruitment_detail.component.RecruitmentPositionAndCountSection
import com.party.guham2.presentation.screens.recruitment_detail.component.RecruitmentTitleSection
import com.party.guham2.presentation.screens.recruitment_detail.state.RecruitmentDetailState
import com.party.guham2.presentation.screens.recruitment_detail.viewmodel.RecruitmentDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecruitmentDetailScreenRoute(
    navController: NavHostController,
    partyRecruitmentId: Int,
    recruitmentDetailViewModel: RecruitmentDetailViewModel = koinViewModel(),
    onTabClick: (MainTab) -> Unit,
){
    val state by recruitmentDetailViewModel.recruitmentDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit){
        recruitmentDetailViewModel.getRecruitmentDetail(partyRecruitmentId = partyRecruitmentId)
    }

    RecruitmentDetailScreen(
        navController = navController,
        state = state,
        onGotoPartyDetail = {},
        onNavigateBack = { navController.popBackStack() },
        onTabClick = onTabClick,
    )
}

@Composable
private fun RecruitmentDetailScreen(
    navController: NavHostController,
    state: RecruitmentDetailState,
    onGotoPartyDetail: () -> Unit,
    onNavigateBack: () -> Unit,
    onTabClick: (MainTab) -> Unit,
){
    val scrollState = rememberScrollState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentMainTab = backStackEntry.toMainTab()

    Scaffold(
        topBar = {
            RecruitmentTitleSection(
                onNavigateBack = onNavigateBack,
                onManageRecruitmentClick = {}
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentMainTab = currentMainTab,
                navController = navController,
                onTabClick = onTabClick
            )
        },
    ){ inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(inner)
            ,
        ) {
            if(state.isLoading){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    RecruitmentImageSection(
                        imageUrl = state.recruitmentDetail.party.image,
                        title = state.recruitmentDetail.party.title,
                        tag = state.recruitmentDetail.party.status,
                        type = state.recruitmentDetail.party.partyType.type,
                        onGoToPartyDetail = onGotoPartyDetail
                    )
                    HeightSpacer(heightDp = 20.dp)

                    RecruitmentInfoSection(
                        recruitingCount = "${state.recruitmentDetail.applicationCount} / ${state.recruitmentDetail.recruitingCount}",
                        applicationCount = state.recruitmentDetail.applicationCount,
                        createDate = state.recruitmentDetail.createdAt
                    )
                    HeightSpacer(heightDp = 32.dp)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = GRAY100,
                        thickness = 12.dp
                    )
                    HeightSpacer(heightDp = 32.dp)

                    RecruitmentPositionAndCountSection(
                        position = "${state.recruitmentDetail.position.main} ${state.recruitmentDetail.position.sub}",
                        recruitingCount = "${state.recruitmentDetail.recruitingCount}명",
                    )

                    HeightSpacer(heightDp = 56.dp)

                    RecruitmentDescription(
                        content = state.recruitmentDetail.content,
                    )
                }
            }
        }
    }
}