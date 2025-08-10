package com.party.guham2.design.component.tab_area

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.BLACK
import com.party.guham2.design.COMPONENT_AREA_HEIGHT
import com.party.guham2.design.GRAY400
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable

val homeTopTabList = listOf("라운지", "파티", "모집공고")

@Composable
fun TabSection(
    modifier: Modifier = Modifier,
    tabList: List<String>,
    selectedTabText: String,
    onClickTab: (String) -> Unit,
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(COMPONENT_AREA_HEIGHT),
            horizontalArrangement = Arrangement.Start
        ) {
            tabList.forEachIndexed { index, title ->
                TabSectionItem(
                    text = title,
                    textColor = if (selectedTabText == title) BLACK else GRAY400,
                    isShowSelectedIndicate = selectedTabText == title,
                    onTabClick = { onClickTab(it) },
                )
                WidthSpacer(widthDp = 40.dp)
            }
        }
    }
}

@Composable
fun TabSectionItem(
    text: String,
    textColor: Color,
    isShowSelectedIndicate: Boolean,
    onTabClick: (String) -> Unit,
){
    val textMeasurer = rememberTextMeasurer()
    val textWidth = textMeasurer.measure(
        text = text,
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    ).size.width

    Column(
        modifier = Modifier
            .wrapContentWidth()
            .height(48.dp)
            .noRippleClickable { onTabClick(text) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
        )

        HeightSpacer(10.dp)

        Box(
            modifier = Modifier
                .width(with(LocalDensity.current) { (textWidth + 20).toDp() })
                .height(4.dp)
                .background(if(isShowSelectedIndicate) PRIMARY else Color.Unspecified)
        )
    }
}