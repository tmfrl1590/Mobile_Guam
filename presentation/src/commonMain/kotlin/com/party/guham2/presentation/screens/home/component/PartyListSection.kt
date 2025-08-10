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
import com.party.guham2.design.component.PartyCard1
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.screens.home.state.HomeState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PartyListSection(
    homeState: HomeState,
    onGoPartyTab: () -> Unit,
    onClickPartyCard: (Int) -> Unit,
) {
    Column {
        HomeSectionHeaderBar(
            title = stringResource(resource = DesignResources.String.new_party),
            description = stringResource(resource = DesignResources.String.new_party_description),
            actionText = stringResource(resource = DesignResources.String.more),
            actionIcon = painterResource(resource = DesignResources.Icon.icon_arrow_right),
            onClickActionIcon = onGoPartyTab
        )

        HeightSpacer(heightDp = 20.dp)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(2.dp),
        ) {
            itemsIndexed(
                items = homeState.partyList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                PartyCard1(
                    imageUrl = item.image,
                    status = item.status,
                    title = item.title,
                    recruitmentCount = item.recruitmentCount,
                    onClick = {onClickPartyCard(item.id)}
                )
            }
        }
    }
}