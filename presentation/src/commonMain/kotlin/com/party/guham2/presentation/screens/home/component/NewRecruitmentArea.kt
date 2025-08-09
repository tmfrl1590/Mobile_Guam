package com.party.guham2.presentation.screens.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.DesignResources
import com.party.guham2.design.component.RecruitmentCard1
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.screens.home.state.HomeState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewRecruitmentArea(
    homeState: HomeState,
    onGoRecruitmentTab: () -> Unit,
    onClickRecruitmentCard: (Int, Int) -> Unit,
) {
    Column {
        HomeSectionHeaderBar(
            title = stringResource(resource = DesignResources.String.new_recruitment),
            description = stringResource(resource = DesignResources.String.new_recruitment_description),
            actionText = stringResource(resource = DesignResources.String.more),
            actionIcon = painterResource(resource = DesignResources.Icon.icon_arrow_right),
            onClickActionIcon = onGoRecruitmentTab
        )

        HeightSpacer(heightDp = 20.dp)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(2.dp),
        ) {
            itemsIndexed(
                items = homeState.recruitmentList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                RecruitmentCard1(
                    imageUrl = item.party.image,
                    category = item.party.partyType.type,
                    title = item.party.title,
                    main = item.position.main,
                    sub = item.position.sub,
                    recruitingCount = item.recruitingCount,
                    recruitedCount = item.recruitedCount,
                    onClick = { onClickRecruitmentCard(item.party.id, item.id) }
                )
            }
        }
    }
}