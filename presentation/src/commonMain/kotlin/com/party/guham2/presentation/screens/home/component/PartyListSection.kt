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
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.component.PartyCard1
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.presentation.model.party.PartyItemModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PartyListSection(
    partyList: List<PartyItemModel>,
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
                items = partyList,
                key = { index, _ ->
                    index
                }
            ) { _, item ->
                PartyCard1(
                    imageUrl = item.image,
                    partyTypeChip = {
                        Chip(
                            containerColor = TYPE_COLOR_BACKGROUND,
                            contentColor = TYPE_COLOR_TEXT,
                            text = item.partyType.type
                        )
                    },
                    title = item.title,
                    recruitmentCount = item.recruitmentCount,
                    onClick = {onClickPartyCard(item.id)}
                )
            }
        }
    }
}