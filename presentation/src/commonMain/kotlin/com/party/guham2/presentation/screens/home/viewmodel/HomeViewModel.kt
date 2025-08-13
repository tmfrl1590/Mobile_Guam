package com.party.guham2.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.design.type.OrderDescType
import com.party.guham2.design.type.PartyType
import com.party.guham2.design.type.SortType
import com.party.guham2.presentation.model.banner.toPresentation
import com.party.guham2.presentation.model.party.toPresentation
import com.party.guham2.presentation.model.recruitment.toPresentation
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.usecase.banner.GetBannerListUseCase
import com.party.guham2.usecase.party.GetPartyListUseCase
import com.party.guham2.usecase.recruitment.GetRecruitmentListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getBannerListUseCase: GetBannerListUseCase,
    private val getRecruitmentListUseCase: GetRecruitmentListUseCase,
    private val getPartyListUseCase: GetPartyListUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getBannerList()
        getRecruitmentList(
            page = 1,
            limit = 10,
            sort = SortType.CREATED_AT.type,
            order = OrderDescType.DESC.type,
            titleSearch = null,
            partyTypes = emptyList(),
            position = emptyList()
        )
        getPartyList(
            page = 1,
            limit = 10,
            sort = SortType.CREATED_AT.type,
            order = OrderDescType.DESC.type,
            partyTypes = emptyList(),
            titleSearch = null,
            status = null
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

    fun getRecruitmentList(
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
            }.onError {

            }
        }
    }

    fun getPartyList(
        page: Int,
        limit: Int,
        sort: String,
        order: String,
        partyTypes: List<Int> = emptyList(),
        titleSearch: String?,
        status: String?
    ){
        viewModelScope.launch(Dispatchers.IO) {
            getPartyListUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                partyTypes = partyTypes,
                titleSearch = titleSearch,
                status = status
            ).onSuccess { result ->
                val partyList = result.parties.map { it.toPresentation() }
                _state.update { it.copy(partyList = partyList) }
            }.onError {

            }
        }
    }

    fun onAction(action: HomeAction) {
        when(action){
            is HomeAction.OnClickTab -> _state.update { it.copy(selectedTabText = action.tabText) }

            // 파티탭 - 파티유형 bottom sheet
            is HomeAction.OnShowPartyTypeBottomSheet -> {
                _state.update { it.copy(isShowPartyTypeBottomSheet = action.isShow) }
            }

            // 파티탭 - 파티유형 bottom sheet 유형 선택
            is HomeAction.OnSelectPartyType -> {
                _state.update { state ->
                    val updatedList = state.selectedPartyTypeList.toMutableList().apply {
                        if(action.partyType == "전체"){
                            clear()
                            add("전체")
                        }else {
                            remove("전체")
                            if (contains(action.partyType)) remove(action.partyType) else add(
                                action.partyType
                            )
                        }
                    }
                    state.copy(selectedPartyTypeList = updatedList)
                }
            }

            // 파티탭 - 파티유형 bottom sheet 초기화
            is HomeAction.OnResetPartyType -> _state.update { it.copy(selectedPartyTypeList = emptyList()) }

            // 파티탭 - 파티유형 bottom sheet 적용하기
            is HomeAction.OnApplyPartyType -> {
                _state.update {
                    it.copy(
                        isShowPartyTypeBottomSheet = false,
                        selectedPartyTypeCount = if(it.selectedPartyTypeList.contains("전체")) 0 else it.selectedPartyTypeList.size,
                    )
                }

                getPartyList(
                    page = 1,
                    limit = 50,
                    sort = SortType.CREATED_AT.type,
                    order = OrderDescType.DESC.type,
                    partyTypes = _state.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = if(_state.value.isOnTogglePartySection) "active" else "archived"
                )
            }

            // 파티탭 - 진행중 토글
            is HomeAction.OnTogglePartySection -> {
                _state.update { it.copy(isOnTogglePartySection = action.isActive) }
                getPartyList(
                    page = 1,
                    limit = 50,
                    sort = SortType.CREATED_AT.type,
                    order = OrderDescType.DESC.type,
                    partyTypes = _state.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = if(_state.value.isOnTogglePartySection) "active" else "archived"
                )
            }

            // 파티탭 - 등록일순 정렬
            is HomeAction.OnDescPartySection -> {
                _state.update { currentState ->
                    val sortedList = if(action.isDesc){
                        currentState.partyList.sortedByDescending { it.createdAt }
                    } else {
                        currentState.partyList.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescPartySection = action.isDesc,
                        partyList = sortedList
                    )
                }
            }
        }
    }
}