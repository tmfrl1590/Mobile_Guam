package com.party.guham2.presentation.screens.join.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.BLACK
import com.party.guham2.design.GRAY100
import com.party.guham2.design.GRAY500
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.dialog.TwoButtonDialog
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.ExplainSection
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.join.action.JoinAction
import com.party.guham2.presentation.screens.join.component.JoinInputField
import com.party.guham2.presentation.screens.join.component.JoinTitleSection
import com.party.guham2.presentation.screens.join.state.JoinState
import com.party.guham2.presentation.screens.join.viewmodel.JoinViewModel

@Composable
fun JoinEmailScreenRoute(
    navController: NavHostController,
    joinViewModel: JoinViewModel,
){
    val joinState by joinViewModel.state.collectAsStateWithLifecycle()

    JoinEmailScreen(
        joinState = joinState,
        onGotoJoinNickName = { navController.navigate(Screens.JoinNickname)},
        onAction = { joinViewModel.onAction(action = it) },
        onConfirmDialog = { navController.popBackStack() }
    )
}

@Composable
fun JoinEmailScreen(
    joinState: JoinState,
    onGotoJoinNickName: () -> Unit,
    onAction: (JoinAction) -> Unit,
    onConfirmDialog: () -> Unit = {},
){
    Scaffold(
        modifier = Modifier
            .blur(
                radiusX = if (joinState.isShowCancelJoinDialog) 10.dp else 0.dp,
                radiusY = if (joinState.isShowCancelJoinDialog) 10.dp else 0.dp,
            )
        ,
        topBar = {
            JoinTitleSection(
                onNavigateBack = {
                    onAction(JoinAction.OnShowCancelJoinDialog(isShow = true))
                },
                actionIcon = {
                    CustomText(
                        text = "1/4",
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
                    mainExplain = "가입을 축하드려요!\n이메일 정보가 맞나요?",
                    subExplain = "나중에 변경할 수 없어요."
                )

                HeightSpacer(heightDp = 40.dp)

                JoinInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                    ,
                    enabled = false,
                    inputText = "tmfrl1590@gmail.com",
                    placeHolder = "",
                    borderColor = GRAY100,
                    containerColor = GRAY100,
                    textStyle = TextStyle(
                        color = GRAY500,
                    ),
                )
            }

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                buttonText = "네, 맞아요",
                buttonTextColor = BLACK,
                containerColor = PRIMARY,
                borderColor = PRIMARY,
                buttonTextSize = B2,
                buttonTextWeight = FontWeight.Bold,
                onClick = onGotoJoinNickName
            )
            HeightSpacer(heightDp = 12.dp)
        }
    }

    if(joinState.isShowCancelJoinDialog){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BLACK.copy(alpha = 0.7f))
                .noRippleClickable {
                    onAction(JoinAction.OnShowCancelJoinDialog(isShow = false))
                }
        ) {
            TwoButtonDialog(
                dialogTitle = "나가기",
                description = "회원가입이 완료되지 않았습니다.\n나가시겠습니까?",
                cancelButtonText = "취소",
                confirmButtonText = "나가기",
                onCancel = {
                    onAction(JoinAction.OnShowCancelJoinDialog(isShow = false))
                },
                onConfirm = {
                    onAction(JoinAction.OnShowCancelJoinDialog(isShow = false))
                    onConfirmDialog()
                }
            )
        }
    }
}