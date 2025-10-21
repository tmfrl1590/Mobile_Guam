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
import com.party.guham2.design.GRAY100
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.screens.recruitment_detail.action.RecruitmentDetailAction
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
    partyId: Int,
    partyRecruitmentId: Int,
    recruitmentDetailViewModel: RecruitmentDetailViewModel = koinViewModel(),
){
    val recruitmentDetailState by recruitmentDetailViewModel.recruitmentDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit){
        recruitmentDetailViewModel.getRecruitmentDetail(partyRecruitmentId = partyRecruitmentId)
    }

    RecruitmentDetailScreen(
        state = recruitmentDetailState,
        onManageClick = {
            //navController.navigate(Screens.RecruitmentEdit(partyId = partyId, partyRecruitmentId = partyRecruitmentId))
        },
        onGotoPartyDetail = {
            //navController.navigate(Screens.PartyDetail(partyId = partyId))
        },
        onAction = { action ->
            when(action){
                is RecruitmentDetailAction.OnNavigationBack -> { navController.popBackStack() }
                is RecruitmentDetailAction.OnApply -> { }
            }
        },
    )
}

@Composable
private fun RecruitmentDetailScreen(
    state: RecruitmentDetailState,
    onManageClick: () -> Unit,
    onGotoPartyDetail: () -> Unit,
    onAction: (RecruitmentDetailAction) -> Unit,
){
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            RecruitmentTitleSection(
                onNavigateBack = {},
                onManageRecruitmentClick = {}
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