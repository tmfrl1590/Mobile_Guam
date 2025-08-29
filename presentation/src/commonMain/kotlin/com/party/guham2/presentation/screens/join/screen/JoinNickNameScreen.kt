package com.party.guham2.presentation.screens.join.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.party.guham2.design.B2
import com.party.guham2.design.B3
import com.party.guham2.design.BLACK
import com.party.guham2.design.DARK100
import com.party.guham2.design.DesignResources
import com.party.guham2.design.GRAY200
import com.party.guham2.design.GRAY400
import com.party.guham2.design.GRAY500
import com.party.guham2.design.LIGHT200
import com.party.guham2.design.LIGHT400
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.RED
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.text.CustomText
import com.party.guham2.design.component.util.ExplainSection
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.design.modifier.noRippleClickable
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.PresentationConstants.NICKNAME_MAX_LENGTH
import com.party.guham2.presentation.screens.join.action.JoinAction
import com.party.guham2.presentation.screens.join.component.JoinInputField
import com.party.guham2.presentation.screens.join.component.JoinTitleSection
import com.party.guham2.presentation.screens.join.state.JoinState
import com.party.guham2.presentation.screens.join.viewmodel.JoinViewModel
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource

@Composable
fun JoinNickNameScreenRoute(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    joinViewModel: JoinViewModel,
){
    val joinState by joinViewModel.state.collectAsStateWithLifecycle()

    // 중복체크 성공하면 생년월일 화면 이동
    LaunchedEffect(key1 = Unit){
        joinViewModel.goToBirthDayScreen.collectLatest {
            navController.navigate(Screens.JoinBirthDay)
        }
    }

    JoinNickNameScreen(
        joinState = joinState,
        snackBarHostState = snackBarHostState,
        onNavigateBack = { navController.popBackStack() },
        onAction = { joinViewModel.onAction(action = it) },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun JoinNickNameScreen(
    joinState: JoinState,
    snackBarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
    onAction: (JoinAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            JoinTitleSection(
                onNavigateBack = onNavigateBack,
                actionIcon = {
                    CustomText(
                        text = "2/4",
                        fontSize = B2,
                        color = GRAY500,
                        modifier = Modifier.padding(end = 20.dp),
                    )
                }
            )
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .background(WHITE)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    focusManager.clearFocus()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MEDIUM_PADDING_SIZE)
            ) {
                Column(
                    modifier = Modifier.
                    weight(1f)
                ) {
                    ExplainSection(
                        mainExplain = "어떻게 불러드리면 될까요?\n닉네임을 입력해 주세요.",
                        subExplain = "닉네임은 나중에 변경할 수 없어요."
                    )

                    HeightSpacer(40.dp)

                    JoinInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        enabled = true,
                        inputText = joinState.userNickName,
                        placeHolder = "15자 이내로 입력해 주세요. (영문/숫자/한글)",
                        borderColor = setInputFieldBorderColor(joinState.userNickName),
                        containerColor = WHITE,
                        textStyle = TextStyle(
                            color = if (joinState.userNickName.isNotEmpty()) BLACK else GRAY400,
                        ),
                        onValueChange = { onAction(JoinAction.OnInputNickName(it)) },
                        trailingIcon = {
                            if(joinState.userNickName.isNotEmpty()){
                                Icon(
                                    painter = painterResource(resource = DesignResources.Icon.icon_close),
                                    contentDescription = "close",
                                    tint = GRAY400,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .noRippleClickable {
                                            onAction(JoinAction.OnResetNickName)
                                        }
                                )
                            }
                        }
                    )
                    HeightSpacer(12.dp)

                    JoinNickNameWarningSection(
                        warningText = joinState.warningMessage,
                        userNickName = joinState.userNickName,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.ime)
                    .padding(horizontal = MEDIUM_PADDING_SIZE, vertical = 12.dp)
            ) {
                Column {
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        buttonText = "다음",
                        buttonTextColor = if (joinState.userNickName.isNotEmpty()) BLACK else GRAY400,
                        containerColor = if (joinState.userNickName.isNotEmpty()) PRIMARY else LIGHT400,
                        borderColor = if (joinState.userNickName.isNotEmpty()) PRIMARY else LIGHT200,
                        buttonTextSize = B2,
                        buttonTextWeight = FontWeight.Bold,
                        onClick = {
                            onAction(JoinAction.OnCheckNickName)
                        }
                    )
                    HeightSpacer(heightDp = 12.dp)
                }
            }
        }
    }
}

@Composable
private fun JoinNickNameWarningSection(
    userNickName: String,
    warningText: String,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomText(
            modifier = Modifier
                .weight(0.8f)
                .padding(start = 6.dp),
            text = warningText,
            fontSize = B3,
            color = RED,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomText(
                text = "${userNickName.length}",
                color = if(userNickName.length > 15 || userNickName.length < 2) RED else GRAY400
            )
            CustomText(
                text = " / ",
                color = GRAY400
            )
            CustomText(
                text = "$NICKNAME_MAX_LENGTH",
                color = GRAY400
            )
        }
    }
}

fun setInputFieldBorderColor(text: String): Color {
    return when {
        text.isEmpty() -> GRAY200
        validNickNameInputField(text) -> DARK100
        else -> RED
    }
}

fun validNickNameInputField(text: String): Boolean {
    if (text.isEmpty() || text.length < 2 || text.length > 15) return false

    // 온전한 한글 음절 (가~힣), 영문 대소문자만 허용
    val regex = Regex("^[a-zA-Z가-힣]+$")

    if (!regex.matches(text)) return false

    // 자음/모음만 입력된 경우 걸러내기 (단일 글자 중 'ㄱ'-'ㅎ', 'ㅏ'-'ㅣ' 포함 여부 검사)
    val invalidJamo = Regex("[ㄱ-ㅎㅏ-ㅣ]")
    return !invalidJamo.containsMatchIn(text)
}