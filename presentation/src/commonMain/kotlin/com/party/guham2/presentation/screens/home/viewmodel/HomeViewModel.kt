package com.party.guham2.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.core.presentation.type.OrderDescType
import com.party.guham2.core.presentation.type.SortType
import com.party.guham2.presentation.model.banner.toPresentation
import com.party.guham2.presentation.model.recruitment.toPresentation
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.usecase.banner.GetBannerListUseCase
import com.party.guham2.usecase.recruitment.GetRecruitmentListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getBannerListUseCase: GetBannerListUseCase,
    private val getRecruitmentListUseCase: GetRecruitmentListUseCase
): ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getBannerList()
        getPersonalRecruitmentList(
            page = 1,
            limit = 10,
            sort = SortType.CREATED_AT.type,
            order = OrderDescType.DESC.type,
            titleSearch = null,
            partyTypes = emptyList(),
            position = emptyList()
        )
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

    fun getPersonalRecruitmentList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        titleSearch: String?,
        partyTypes: List<Int> = emptyList(),
        position: List<Int> = emptyList(),
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecruitmentListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                titleSearch = titleSearch,
                partyTypes = partyTypes,
                position = position
            ).onSuccess { result ->
                val recruitmentList = result.partyRecruitments.map { it.toPresentation() }
                _state.update { it.copy(recruitmentList = recruitmentList) }
            }
        }
    }

    fun onAction(action: HomeAction) {
        when(action){
            is HomeAction.OnClickTab -> _state.update { it.copy(selectedTabText = action.tabText) }
        }
    }
}