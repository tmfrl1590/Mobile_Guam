package com.party.guham2.presentation.screens.join.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.T1
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.join.component.JoinTitleSection

@Composable
fun JoinCompleteScreenRoute(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
){

    JoinCompleteScreen(
        snackBarHostState = snackBarHostState,
        onGotoHome = { navController.navigate(Screens.Main)},
        onGotoDetailProfile = {}
    )
}

@Composable
private fun JoinCompleteScreen(
    snackBarHostState: SnackbarHostState,
    onGotoHome: () -> Unit,
    onGotoDetailProfile: () -> Unit
){
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            JoinTitleSection(
                title = "가입완료"
            )
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
                CustomText(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    text = "가입을 축하합니다!\n세부 프로필을 작성해 볼까요?",
                    fontWeight = FontWeight.Bold,
                    fontSize = T1,
                )
            }
        }

        JoinCompleteButtonSection(
            onGotoHome = onGotoHome,
            onGotoDetailProfile = onGotoDetailProfile
        )

        HeightSpacer(heightDp = 12.dp)
    }
}

@Composable
private fun JoinCompleteButtonSection(
    onGotoHome: () -> Unit,
    onGotoDetailProfile: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        CustomButton(
            modifier = Modifier
                .weight(0.5f)
            ,
            buttonText = "홈으로",
            buttonTextColor = BLACK,
            containerColor = WHITE,
            borderColor = PRIMARY,
            buttonTextSize = B2,
            buttonTextWeight = FontWeight.Bold,
            onClick = onGotoHome
        )

        CustomButton(
            modifier = Modifier
                .weight(0.5f)
            ,
            buttonText = "작성하기",
            buttonTextColor = BLACK,
            containerColor = PRIMARY,
            borderColor = PRIMARY,
            buttonTextSize = B2,
            buttonTextWeight = FontWeight.Bold,
            onClick = onGotoDetailProfile
        )
    }
}