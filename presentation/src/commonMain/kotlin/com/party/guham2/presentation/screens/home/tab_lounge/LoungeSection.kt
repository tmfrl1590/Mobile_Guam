package com.party.guham2.presentation.screens.home.tab_lounge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.model.banner.BannerItemModel
import com.party.guham2.presentation.model.party.PartyItemModel
import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel
import com.party.guham2.presentation.screens.home.component.BannerSection
import com.party.guham2.presentation.screens.home.component.NewRecruitmentSection
import com.party.guham2.presentation.screens.home.component.PartyListSection

@Composable
fun LoungeSection(
    bannerList: List<BannerItemModel>,
    partyList: List<PartyItemModel>,
    recruitmentList: List<RecruitmentItemModel>,
    onGoRecruitmentTab: () -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
    onGotoPartyTab: () -> Unit,
    onClickPartyCard: (Int) -> Unit
){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        BannerSection(bannerList = bannerList)

        HeightSpacer(heightDp = 40.dp)

        NewRecruitmentSection(
            recruitmentList = recruitmentList,
            onClickRecruitmentCard = onClickRecruitmentCard,
            onGoRecruitmentTab = onGoRecruitmentTab
        )

        HeightSpacer(heightDp = 60.dp)

        PartyListSection(
            partyList = partyList,
            onGoPartyTab = onGotoPartyTab,
            onClickPartyCard = onClickPartyCard
        )

        HeightSpacer(heightDp = 60.dp)
    }
}

