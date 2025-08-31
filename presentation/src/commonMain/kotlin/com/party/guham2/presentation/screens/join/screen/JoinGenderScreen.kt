package com.party.guham2.presentation.screens.join.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY500
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.ExplainSection
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.join.action.JoinAction
import com.party.guham2.presentation.screens.join.component.JoinTitleSection
import com.party.guham2.presentation.screens.join.component.SelectGenderSection
import com.party.guham2.presentation.screens.join.state.JoinState
import com.party.guham2.presentation.screens.join.viewmodel.JoinViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun JoinGenderScreenRoute(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    joinViewModel: JoinViewModel,
){
    val joinState by joinViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit){
        joinViewModel.goToJoinCompleteScreen.collectLatest {
            navController.navigate(route = Screens.JoinComplete)
        }
    }

    JoinGenderScreen(
        joinState = joinState,
        snackBarHostState = snackBarHostState,
        onNavigateBack = { navController.popBackStack() },
        onAction = { joinViewModel.onAction(action = it) },
    )
}

@Composable
private fun JoinGenderScreen(
    joinState: JoinState,
    snackBarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
    onAction: (JoinAction) -> Unit,
){
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            JoinTitleSection(
                onNavigateBack = onNavigateBack,
                actionIcon = {
                    CustomText(
                        text = "4/4",
                        fontSize = B2,
                        color = GRAY500,
                        modifier = Modifier.padding(end = 20.dp),
                    )
                }
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
                ExplainSection(
                    mainExplain = "성별은\n어떻게 되시나요?",
                    subExplain = "프로필에서 노출 여부를 설정할 수 있어요."
                )

                HeightSpacer(heightDp = 40.dp)

                SelectGenderSection(
                    selectedGender = joinState.gender,
                    onSelect = { gender ->
                        onAction(JoinAction.OnSelectGender(gender = gender))
                    }
                )
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                buttonText = "완료",
                buttonTextColor = BLACK,
                containerColor = PRIMARY,
                borderColor = PRIMARY,
                buttonTextSize = B2,
                buttonTextWeight = FontWeight.Bold,
                onClick = { onAction(JoinAction.OnJoinComplete) }
            )
            HeightSpacer(heightDp = 12.dp)
        }
    }
}