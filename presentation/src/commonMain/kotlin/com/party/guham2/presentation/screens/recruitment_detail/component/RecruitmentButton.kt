package com.party.guham2.presentation.screens.recruitment_detail.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.party.guham2.design.B2
import com.party.guham2.design.GRAY300
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.component.button.CustomButton

@Composable
fun RecruitmentButton(
    isRecruitmented: Boolean,
    onClick: () -> Unit,
) {
    CustomButton(
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = MEDIUM_PADDING_SIZE),
        buttonText = if(isRecruitmented) "지원완료" else "지원하기",
        containerColor = if(isRecruitmented) GRAY300 else PRIMARY,
        borderColor = if(isRecruitmented) GRAY300 else PRIMARY,
        buttonTextSize = B2,
        buttonTextWeight = FontWeight.Bold,
        onClick = { if(!isRecruitmented) onClick() }
    )
}