package com.party.guham2.design.component.bottomsheet.component.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.DesignResources
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

val partyTypeList = listOf(
    "전체" to 0,
    "스터디" to 1,
    "포트폴리오" to 2,
    "해커톤" to 3,
    "공모전" to 4,
)

@Composable
fun BottomSheetPartyTypeSection(
    selectedPartyTypeList: List<String>,
    onClickPartyType: (String) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        itemsIndexed(
            items = partyTypeList,
            key = { index, _ ->
                index
            }
        ) { _, item ->
            BottomSheetPartyTypeItem(
                text = item,
                selectedPartyTypeList = selectedPartyTypeList,
                onClickPartyType = {onClickPartyType(it)}
            )
        }
    }
}

@Composable
private fun BottomSheetPartyTypeItem(
    text: Pair<String, Int>,
    selectedPartyTypeList: List<String>,
    onClickPartyType: (String) -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .noRippleClickable {
                onClickPartyType(text.first)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = if(selectedPartyTypeList.contains(text.first)) painterResource(resource = DesignResources.Icon.icon_checked) else painterResource(resource = DesignResources.Icon.icon_unchecked),
            contentDescription = "check",
        )
        WidthSpacer(widthDp = 6.dp)
        CustomText(
            text = text.first,
            fontWeight = if(selectedPartyTypeList.contains(text.first)) FontWeight.Bold else FontWeight.Normal,
            fontSize = B1,
        )
    }
}