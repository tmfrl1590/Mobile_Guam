package com.party.guham2.presentation.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.usecase.user.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    fun login(idToken: String){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(
                accessTokenRequest = AccessTokenRequest(
                    idToken = idToken
                )
            )
        }
    }
}