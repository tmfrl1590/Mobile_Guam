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
import com.party.guham2.presentation.model.user.toPresentation
import com.party.guham2.presentation.screens.home.action.HomeAction
import com.party.guham2.presentation.screens.home.action.HomeRecruitmentAction
import com.party.guham2.presentation.screens.home.state.HomeState
import com.party.guham2.presentation.screens.home.state.PartyState
import com.party.guham2.presentation.screens.home.state.RecruitmentState
import com.party.guham2.usecase.banner.GetBannerListUseCase
import com.party.guham2.usecase.party.GetPartyListUseCase
import com.party.guham2.usecase.recruitment.GetRecruitmentListUseCase
import com.party.guham2.usecase.user.GetPositionListUseCase
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
    private val getPositionListUseCase: GetPositionListUseCase,
): ViewModel(){

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _partyState = MutableStateFlow(PartyState())
    val partyState = _partyState.asStateFlow()

    private val _recruitmentState = MutableStateFlow(RecruitmentState())
    val recruitmentState = _recruitmentState.asStateFlow()

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
                    _homeState.update { it.copy(bannerList = bannerList) }
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
                _recruitmentState.update { it.copy(recruitmentList = recruitmentList) }
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
                _partyState.update { it.copy(partyList = partyList) }
            }.onError {

            }
        }
    }

    fun getPositionList(main: String){
        viewModelScope.launch(Dispatchers.IO) {
            getPositionListUseCase(main = main)
                .onSuccess { result ->
                    val positionList = result.map { it.toPresentation() }
                    _recruitmentState.update { it.copy(mainAndSubPositionList = positionList) }
                }.onError { error ->

                }
        }
    }

    fun onRecruitmentAction(action: HomeRecruitmentAction){
        when(action){
            is HomeRecruitmentAction.OnShowPositionBottomSheet -> _recruitmentState.update { it.copy(isShowPositionBottomSheet = action.isShow) }
            is HomeRecruitmentAction.OnSelectMainPosition -> _recruitmentState.update { it.copy(selectedMainPosition = action.mainPosition) }
            is HomeRecruitmentAction.OnSelectSubPosition -> {
                _recruitmentState.update { currentState ->
                    val updatedSubPositionList = currentState.selectedSubPositionList.toMutableList().apply {
                        if (any { it.sub == action.subPosition }) {
                            // 이미 선택된 서브 포지션을 제거
                            clear()
                            addAll(currentState.selectedSubPositionList.filter { it.sub != action.subPosition })
                        } else {
                            // 새로운 서브 포지션을 추가
                            currentState.mainAndSubPositionList.find { it.sub == action.subPosition }?.let { add(it) }
                        }
                    }

                    val updatedMainAndSubPosition = currentState.selectedMainAndSubPosition.toMutableList().apply {
                        // 서브 포지션 클릭으로 업데이트된 조합을 반영
                        clear()
                        addAll(currentState.selectedMainAndSubPosition.filter { pair ->
                            !(pair.first == currentState.selectedMainPosition && pair.second == action.subPosition)
                        }) // 중복 제거
                        updatedSubPositionList.find { it.sub == action.subPosition }?.let {
                            add(Pair(currentState.selectedMainPosition, it.sub))
                        }
                    }


                    currentState.copy(
                        selectedSubPositionList = updatedSubPositionList,
                        selectedMainAndSubPosition = updatedMainAndSubPosition
                    )
                }
            }
            is HomeRecruitmentAction.OnDeleteSelectedMainAndSubPosition -> {
                _recruitmentState.update { currentState ->
                    val updatedSubPositionList = currentState.selectedSubPositionList.toMutableList().apply {
                        clear()
                        addAll(currentState.selectedSubPositionList.filter { it.sub != action.position.second })
                    }

                    val updatedMainAndSubPosition = currentState.selectedMainAndSubPosition.toMutableList().apply {
                        clear()
                        addAll(currentState.selectedMainAndSubPosition.filter { pair ->
                            !(pair.first == action.position.first && pair.second == action.position.second)
                        })
                    }

                    currentState.copy(
                        selectedSubPositionList = updatedSubPositionList,
                        selectedMainAndSubPosition = updatedMainAndSubPosition
                    )
                }
            }
            is HomeRecruitmentAction.OnResetSelectedMainAndSubPosition -> {
                _recruitmentState.update {
                    it.copy(
                        selectedMainPosition = "전체",
                        selectedSubPositionList = emptyList(),
                        selectedMainAndSubPosition = emptyList()
                    )
                }
            }
            is HomeRecruitmentAction.OnApplyMainAndSubPosition -> {
                _recruitmentState.update { it.copy(isShowPositionBottomSheet = false) }
                val matchingIds = _recruitmentState.value.selectedSubPositionList.filter { position ->
                    _recruitmentState.value.selectedMainAndSubPosition.any { it.second == position.sub }
                }.map { it.id }

                _recruitmentState.update { it.copy(selectedPositionCount = _recruitmentState.value.selectedSubPositionList.size) }

                getRecruitmentList(
                    page = 1,
                    limit = 50,
                    sort = SortType.CREATED_AT.type,
                    order = OrderDescType.DESC.type,
                    titleSearch = null,
                    partyTypes = _recruitmentState.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    position = matchingIds
                )
            }
            is HomeRecruitmentAction.OnApplyPartyType -> {
                _recruitmentState.update {
                    it.copy(
                        isShowPartyTypeBottomSheet = false,
                        selectedPartyTypeCount = if(it.selectedPartyTypeList.contains("전체")) 0 else it.selectedPartyTypeList.size,
                    )
                }

                getRecruitmentList(
                    page = 1,
                    limit = 50,
                    sort = SortType.CREATED_AT.type,
                    order = OrderDescType.DESC.type,
                    partyTypes = _recruitmentState.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                )
            }
            is HomeRecruitmentAction.OnResetPartyType -> _recruitmentState.update { it.copy(selectedPartyTypeList = emptyList()) }
            is HomeRecruitmentAction.OnSelectPartyType -> {
                _recruitmentState.update { state ->
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
            is HomeRecruitmentAction.OnDescPartySection -> {
                _recruitmentState.update { currentState ->
                    val sortedList = if(action.isDesc){
                        currentState.recruitmentList.sortedByDescending { it.createdAt }
                    } else {
                        currentState.recruitmentList.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        isDescPartySection = action.isDesc,
                        recruitmentList = sortedList
                    )
                }
            }
            is HomeRecruitmentAction.OnShowPartyTypeBottomSheet -> _recruitmentState.update { it.copy(isShowPartyTypeBottomSheet = action.isShow) }
        }
    }

    fun onPartyAction(action: HomeAction) {
        when(action){
            is HomeAction.OnClickTab -> _homeState.update { it.copy(selectedTabText = action.tabText) }
            is HomeAction.OnExpandedFloating -> _homeState.update { it.copy(isExpandedFloating = action.isExpandedFloating) }

            // 파티탭 - 파티유형 bottom sheet
            is HomeAction.OnShowPartyTypeBottomSheet -> _partyState.update { it.copy(isShowPartyTypeBottomSheet = action.isShow) }

            // 파티탭 - 파티유형 bottom sheet 유형 선택
            is HomeAction.OnSelectPartyType -> {
                _partyState.update { state ->
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
            is HomeAction.OnResetPartyType -> _partyState.update { it.copy(selectedPartyTypeList = emptyList()) }

            // 파티탭 - 파티유형 bottom sheet 적용하기
            is HomeAction.OnApplyPartyType -> {
                _partyState.update {
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
                    partyTypes = _partyState.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = if(_partyState.value.isOnTogglePartySection) "active" else "archived"
                )
            }

            // 파티탭 - 진행중 토글
            is HomeAction.OnTogglePartySection -> {
                _partyState.update { it.copy(isOnTogglePartySection = action.isActive) }
                getPartyList(
                    page = 1,
                    limit = 50,
                    sort = SortType.CREATED_AT.type,
                    order = OrderDescType.DESC.type,
                    partyTypes = _partyState.value.selectedPartyTypeList.mapNotNull { type ->
                        PartyType.entries.find { it.type == type }?.id
                    },
                    titleSearch = null,
                    status = if(_partyState.value.isOnTogglePartySection) "active" else "archived"
                )
            }

            // 파티탭 - 등록일순 정렬
            is HomeAction.OnDescPartySection -> {
                _partyState.update { currentState ->
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