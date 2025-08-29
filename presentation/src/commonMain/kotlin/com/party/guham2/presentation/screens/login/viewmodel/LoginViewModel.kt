package com.party.guham2.presentation.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.DataErrorRemote
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.model.user.login.AccessTokenRequest
import com.party.guham2.model.user.login.LoginFailure
import com.party.guham2.usecase.user.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private val _loginSuccess = MutableSharedFlow<Unit>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    private val _goToJoin = MutableSharedFlow<LoginFailure>()
    val goToJoin = _goToJoin.asSharedFlow()

    fun login(idToken: String, email: String){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(
                accessTokenRequest = AccessTokenRequest(
                    idToken = idToken
                )
            ).onSuccess {
                _loginSuccess.emit(Unit)
            }.onError { error ->
                when (error) {
                    is DataErrorRemote.Unauthorized -> {
                        val loginFailure = LoginFailure(
                            userEmail = email,
                            message = error.response?.message ?: "",
                            signupAccessToken = error.response?.signupAccessToken ?: ""
                        )
                        _goToJoin.emit(value = loginFailure)
                    }
                    else -> { }
                }
            }
        }
    }
}