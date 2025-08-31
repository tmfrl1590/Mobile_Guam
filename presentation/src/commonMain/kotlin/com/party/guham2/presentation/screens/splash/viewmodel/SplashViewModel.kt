package com.party.guham2.presentation.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.presentation.screens.splash.state.SplashState
import com.party.guham2.usecase.local.GetAccessTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
): ViewModel() {

    private val _splashState = MutableStateFlow(SplashState())
    val splashState = _splashState.asStateFlow()

    init {
        getAccessToken()
    }

    fun getAccessToken(){
        viewModelScope.launch(Dispatchers.IO) {
            val accessToken = getAccessTokenUseCase()
            _splashState.update { it.copy(accessToken = accessToken) }
        }
    }
}