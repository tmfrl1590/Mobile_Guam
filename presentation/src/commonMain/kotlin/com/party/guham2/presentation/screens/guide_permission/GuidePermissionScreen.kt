package com.party.guham2.presentation.screens.guide_permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY100
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionDescriptionSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionHeaderSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionListSection
import com.party.guham2.presentation.screens.guide_permission.component.GuidePermissionTitleSection
import com.party.guham2.presentation.screens.guide_permission.viewmodel.GuidePermissionViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GuidePermissionScreenRoute(
    navController: NavHostController,
    guidePermissionViewModel: GuidePermissionViewModel = koinViewModel()
){
    GuidePermissionScreen(
        onConfirm = {
            navController.navigate(Screens.Login){
                popUpTo(Screens.GuidePermission) { inclusive = true}
            }
        }
    )
}

@Composable
private fun GuidePermissionScreen(
    onConfirm: () -> Unit,
){
    Scaffold(
        topBar = {
            GuidePermissionHeaderSection()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(it)
                .padding(horizontal = MEDIUM_PADDING_SIZE)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                HeightSpacer(32.dp)
                GuidePermissionTitleSection()
                HeightSpacer(40.dp)
                GuidePermissionListSection()
                HeightSpacer(20.dp)

                HorizontalDivider(
                    thickness = 1.dp,
                    color = GRAY100
                )

                HeightSpacer(20.dp)

                GuidePermissionDescriptionSection()
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                buttonText = "확인",
                buttonTextWeight = FontWeight.Bold,
                buttonTextSize = B2,
                containerColor = PRIMARY,
                buttonTextColor = BLACK,
                onClick = onConfirm
            )
            HeightSpacer(12.dp)
        }
    }
}
