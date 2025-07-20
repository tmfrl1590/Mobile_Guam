package com.party.guham2.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.model.banner.toPresentation
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.usecase.banner.GetBannerListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getBannerListUseCase: GetBannerListUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getBannerList()
    }

    fun getBannerList(){
        viewModelScope.launch(Dispatchers.IO) {
            getBannerListUseCase()
                .onSuccess { result ->
                    val bannerList = result.toPresentation().bannerList
                    _state.update { it.copy(bannerList = bannerList) }
                }
        }
    }

    fun onAction(action: HomeAction) {
        when(action){
            is HomeAction.OnClickTab -> _state.update { it.copy(selectedTabText = action.tabText) }
        }
    }
}