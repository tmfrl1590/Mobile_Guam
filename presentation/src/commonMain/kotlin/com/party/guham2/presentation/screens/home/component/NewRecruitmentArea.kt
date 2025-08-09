package com.party.guham2.presentation.screens.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY100
import com.party.guham2.design.GRAY400
import com.party.guham2.design.ImageLoading
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.MEDIUM_CORNER_SIZE
import com.party.guham2.design.RED
import com.party.guham2.design.T3
import com.party.guham2.design.TYPE_COLOR_BACKGROUND
import com.party.guham2.design.TYPE_COLOR_TEXT
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.chip.Chip
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.component.util.WidthSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.presentation.model.recruitment.RecruitmentItemModel
import com.party.guham2.presentation.screens.home.state.HomeState
import guamapplication.presentation.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewRecruitmentArea(
    homeState: HomeState,
    onGoRecruitment: () -> Unit,
    onClick: (Int, Int) -> Unit,
) {
    Column {
        HomeSectionHeaderBar(
            title = stringResource(resource = DesignResources.String.new_recruitment),
            description = stringResource(resource = DesignResources.String.new_recruitment_description),
            actionText = stringResource(resource = DesignResources.String.more),
            actionIcon = painterResource(resource = DesignResources.Icon.icon_arrow_right)
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
                RecruitmentItem(
                    recruitmentItemModel = item,
                    onClick = onClick,
                )
            }
        }
    }
}

@Composable
private fun RecruitmentItem(
    recruitmentItemModel: RecruitmentItemModel,
    onClick: (Int, Int) -> Unit
){
    Card(
        onClick = { onClick(recruitmentItemModel.party.id, recruitmentItemModel.id) },
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = WHITE
        ),
        border = BorderStroke(1.dp, GRAY100),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .width(224.dp)
                .height(311.dp)
                .padding(12.dp),
        ) {
            RecruitmentItemTopArea(
                imageUrl = recruitmentItemModel.party.image,
            )
            HeightSpacer(heightDp = 12.dp)

            RecruitmentItemBottomArea(
                category = recruitmentItemModel.party.partyType.type,
                title = recruitmentItemModel.party.title,
                main = recruitmentItemModel.position.main,
                sub = recruitmentItemModel.position.sub,
                recruitingCount = recruitmentItemModel.recruitingCount,
                recruitedCount = recruitmentItemModel.recruitedCount,
                onClick = { onClick(recruitmentItemModel.party.id, recruitmentItemModel.id) },
            )
        }
    }
}

@Composable
private fun RecruitmentItemTopArea(
    imageUrl: String? = null,
) {
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        imageUrl = imageUrl,
        roundedCornerShape = MEDIUM_CORNER_SIZE
    )
}

@Composable
fun RecruitmentItemBottomArea(
    category: String,
    title: String,
    main: String,
    sub: String,
    recruitingCount: Int,
    recruitedCount: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
        ) {
            Chip(
                containerColor = TYPE_COLOR_BACKGROUND,
                contentColor = TYPE_COLOR_TEXT,
                text = category,
            )
            HeightSpacer(heightDp = 4.dp)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 6.dp)
                ,
                text = title,
                fontSize = T3,
                fontWeight = FontWeight.Bold,
            )
            HeightSpacer(heightDp = 4.dp)

            PositionArea(
                modifier = Modifier
                    .height(20.dp)
                    .padding(start = 6.dp),
                main = main,
                sub = sub,
                onClick = onClick
            )
        }

        HeightSpacer(heightDp = 12.dp)

        RecruitmentCountArea(
            modifier = Modifier
                .height(17.dp),
            recruitingCount = recruitingCount,
            recruitedCount = recruitedCount,
            onClick = onClick
        )
    }
}

@Composable
fun PositionArea(
    modifier: Modifier,
    main: String,
    sub: String,
    textColor: Color = BLACK,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .noRippleClickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = main,
            fontSize = B2,
            color = textColor,
        )
        WidthSpacer(widthDp = 6.dp)
        Image(
            painter = painterResource(DesignResources.Image.image_vertical),
            contentDescription = "",
            modifier = Modifier
                .width(1.dp)
                .height(10.dp)
                .padding(top = 2.dp)
                .background(GRAY400)
            ,
        )
        WidthSpacer(widthDp = 2.dp)
        Text(
            text = main,
            fontSize = B2,
            color = textColor,
        )
    }
}

@Composable
fun RecruitmentCountArea(
    modifier: Modifier,
    recruitingCount: Int,
    recruitedCount: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
    ) {
        Text(
            text = "모집중",
            fontSize = B3,
        )

        WidthSpacer(widthDp = 4.dp)

        Text(
            text = "$recruitedCount / $recruitingCount",
            fontSize = B3,
            color = RED
        )

    }
}