package com.party.guham2.design.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B1
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY100
import com.party.guham2.design.LIGHT400
import com.party.guham2.design.component.bottomsheet.component.BottomSheetTitleSection
import com.party.guham2.design.component.bottomsheet.component.ResetAndApplySection
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import org.jetbrains.compose.resources.painterResource

val positionList = listOf(
    "기획자",
    "디자이너",
    "개발자",
    "마케터/광고",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPositionBottomSheet(
    selectedMainPosition: String,
    subPositionList: List<Pair<String, Int>>,
    selectedSubPositionList: List<Pair<String, Int>>,
    selectedMainAndSubPositionList: List<Pair<String, String>>,
    onClosePositionBottomSheet: () -> Unit,
    onClickMainPosition: (String) -> Unit,
    onClickSubPosition: (String) -> Unit,
    onDelete: (Pair<String, String>) -> Unit,
    onReset: () -> Unit = {},
    onApply: () -> Unit,
){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onClosePositionBottomSheet() },
        containerColor = White,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            BottomSheetTitleSection(
                title = "직무",
                onClosePositionBottomSheet = onClosePositionBottomSheet
            )

            PositionSelectArea(
                selectedMainPosition = selectedMainPosition,
                subPositionList = subPositionList,
                selectedSubPositionList = selectedSubPositionList,
                onClickMainPosition = onClickMainPosition,
                onClickSubPosition = onClickSubPosition
            )
        }

        SelectedPositionArea(
            selectedPositionList = selectedMainAndSubPositionList,
            onDelete = onDelete,
        )

        ResetAndApplySection(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            onReset = onReset,
            onApply = onApply
        )
    }
}

@Composable
private fun PositionSelectArea(
    selectedMainPosition: String,
    subPositionList: List<Pair<String, Int>>,
    selectedSubPositionList: List<Pair<String, Int>>,
    onClickMainPosition: (String) -> Unit,
    onClickSubPosition: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MainPositionListArea(
            modifier = Modifier.weight(0.2f),
            selectedMainPosition = selectedMainPosition,
            onClickMainPosition = onClickMainPosition,
        )

        SubPositionListArea(
            modifier = Modifier.weight(0.3f),
            subPositionList = subPositionList,
            selectedSubPositionList = selectedSubPositionList,
            onClickSubPosition = onClickSubPosition
        )
    }
}

@Composable
private fun MainPositionListArea(
    modifier: Modifier,
    selectedMainPosition: String,
    onClickMainPosition: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = positionList,
            key = { index, _ ->
                index
            }
        ) { _, item ->
            MainPositionListItem(
                modifier = Modifier.fillMaxWidth(),
                text = item,
                selectedMainPosition = selectedMainPosition,
                containerColor = if (item == selectedMainPosition) LIGHT400 else White,
                onClick = { onClickMainPosition(it) }
            )
        }
    }
}

@Composable
private fun SubPositionListArea(
    modifier: Modifier,
    subPositionList: List<Pair<String, Int>>,
    selectedSubPositionList: List<Pair<String, Int>>,
    onClickSubPosition: (String) -> Unit,
){
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(
            items = subPositionList,
            key = { index, _ ->
                index
            }
        ) { index, item ->
            SubPositionListItem(
                sub = item.first,
                selectedSubPositionList = selectedSubPositionList,
                onClickSubPosition = { onClickSubPosition(it) }
            )
        }
    }
}

@Composable
private fun SubPositionListItem(
    sub: String,
    selectedSubPositionList: List<Pair<String, Int>>,
    onClickSubPosition: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(52.dp)
            .padding(start = 20.dp)
            .noRippleClickable { onClickSubPosition(sub) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            contentDescription = "check icon",
            painter = if (selectedSubPositionList.any { it.first == sub }) {
                painterResource(resource = DesignResources.Icon.icon_checked)
            } else {
                painterResource(resource = DesignResources.Icon.icon_unchecked)
            },
        )
        WidthSpacer(widthDp = 6.dp)
        Text(
            text = sub,
            color = BLACK,
            fontWeight = if (selectedSubPositionList.any { it.first == sub }) FontWeight.Bold else FontWeight.Normal,
            fontSize = B1,
        )
    }
}

@Composable
private fun MainPositionListItem(
    modifier: Modifier,
    text: String,
    selectedMainPosition: String,
    containerColor: Color,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable { onClick(text) }
            .background(containerColor)
            .padding(start = 20.dp, end = 12.dp)
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            fontWeight = if (text == selectedMainPosition) FontWeight.Bold else FontWeight.Normal,
        )
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(resource = DesignResources.Icon.icon_arrow_right),
            contentDescription = "arrow right",
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectedPositionArea(
    selectedPositionList: List<Pair<String, String>>,
    onDelete: (Pair<String, String>) -> Unit,
) {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 10.dp)
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (i in 1..selectedPositionList.size){
            SelectedPositionItem(
                selectedPosition = selectedPositionList[i - 1],
                onDelete = { onDelete(it) }
            )
        }
    }
}

@Composable
private fun SelectedPositionItem(
    selectedPosition: Pair<String, String>,
    onDelete: (Pair<String, String>) -> Unit,
) {
    Card(
        onClick = { onDelete(selectedPosition) },
        modifier = Modifier
            .wrapContentWidth()
            .height(32.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = GRAY100,
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "${selectedPosition.first} | ${selectedPosition.second}",
                color = BLACK,
                fontSize = B2,
            )
            WidthSpacer(widthDp = 4.dp)
            IconButton(
                modifier = Modifier.size(16.dp),
                onClick = { onDelete(selectedPosition) }
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(resource = DesignResources.Icon.icon_close2),
                    contentDescription = "close icon",
                    tint = Color.Gray
                )
            }
        }
    }
}