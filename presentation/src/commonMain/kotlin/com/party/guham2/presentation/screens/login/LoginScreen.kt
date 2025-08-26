package com.party.guham2.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.party.guham2.design.MEDIUM_PADDING_SIZE
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.util.ExplainSection
import com.party.guham2.design.component.util.HeightSpacer
import com.party.guham2.navigation.Screens
import com.party.guham2.presentation.screens.login.component.LoginBottomSection
import com.party.guham2.presentation.screens.login.component.LoginButtonSection
import com.party.guham2.presentation.screens.login.component.LoginTitleSection
import com.party.guham2.presentation.screens.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel()
){
    // 로그인 성공
    LaunchedEffect(key1 = Unit) {
        loginViewModel.loginSuccess.collectLatest {
            navController.navigate(Screens.Main)
        }
    }

    LoginScreen(
        onGotoTerms = {  },
        onGotoInquire = { },
        onPrivacyClick = { },
        onGoogleLoginSuccess = {
            loginViewModel.login(it)
        }
    )
}

@Composable
private fun LoginScreen(
    onGotoTerms: () -> Unit,
    onGotoInquire: () -> Unit,
    onPrivacyClick: () -> Unit,
    onGoogleLoginSuccess: (String) -> Unit,
){
    Scaffold(
        topBar = {
            LoginTitleSection()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WHITE)
                .padding(it)
                .padding(horizontal = MEDIUM_PADDING_SIZE)
        ) {
            ExplainSection(
                mainExplain = "파티괌과 함께\n파티에 참여하실 준비가 되었나요?",
                subExplain = "소셜 로그인으로 편하게 이용해보세요."
            )

            HeightSpacer(heightDp = 40.dp)

            LoginButtonSection(
                onGoogleLoginSuccess = onGoogleLoginSuccess
            )

            HeightSpacer(heightDp = 24.dp)

            LoginBottomSection(
                onGotoInquire = onGotoInquire,
                onTermsClick = onGotoTerms,
                onPrivacyClick = onPrivacyClick,
            )
        }
    }
}