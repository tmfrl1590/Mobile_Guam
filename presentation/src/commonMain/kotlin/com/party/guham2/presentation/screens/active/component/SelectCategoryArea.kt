package com.party.guham2.presentation.screens.active.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY200
import com.party.guham2.design.GRAY500
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.BorderChip

@Composable
fun SelectCategoryArea(
    categoryList: List<String>,
    selectedCategory: String,
    onClick: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(WHITE),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(categoryList) { _, category ->
            BorderChip(
                borderColor = if (selectedCategory == category) PRIMARY else GRAY200,
                fontWeight = if (selectedCategory == category) FontWeight.Bold else FontWeight.Normal,
                text = category,
                textColor = if (selectedCategory == category) BLACK else GRAY500,
                contentColor = BLACK,
                onClick = { onClick(category) }
            )
        }
    }
}