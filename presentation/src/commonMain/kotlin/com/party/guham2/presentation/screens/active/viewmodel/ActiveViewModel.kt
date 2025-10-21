package com.party.guham2.presentation.screens.active.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.model.user.party.toPresentation
import com.party.guham2.presentation.screens.active.action.MyPartyAction
import com.party.guham2.presentation.screens.active.state.MyPartyState
import com.party.guham2.usecase.user.party.GetMyPartyUseCase
import com.party.guham2.usecase.user.party.GetMyRecruitmentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActiveViewModel(
    private val getMyPartyUseCase: GetMyPartyUseCase,
    private val getMyRecruitmentUseCase: GetMyRecruitmentUseCase,
) : ViewModel() {

    private val _myPartyState = MutableStateFlow(MyPartyState())
    val myPartyState = _myPartyState.asStateFlow()

    private val _successCancel = MutableSharedFlow<Unit>()
    val successCancel = _successCancel.asSharedFlow()

    fun getMyParty(page: Int, limit: Int, sort: String, order: String, status: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _myPartyState.update { it.copy(isMyPartyLoading = true) }

            getMyPartyUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order,
                status = status
            ).onSuccess { result ->
                _myPartyState.update {
                    it.copy(
                        myPartyList = result.toPresentation(),
                        isMyPartyLoading = false
                    )
                }
            }.onError {
                _myPartyState.update { it.copy(isMyPartyLoading = false) }
            }
        }
    }

    fun getMyRecruitment(page: Int, limit: Int, sort: String, order: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _myPartyState.update { it.copy(isMyRecruitmentLoading = true) }

            getMyRecruitmentUseCase(
                page = page,
                limit = limit,
                sort = sort,
                order = order
            ).onSuccess { result ->
                _myPartyState.update {
                    it.copy(
                        myRecruitmentList = result.toPresentation(),
                        isMyRecruitmentLoading = false
                    )
                }
            }.onError {
                _myPartyState.update {
                    it.copy(
                        isMyRecruitmentLoading = false
                    )
                }
            }
        }
    }

    fun onAction(action: MyPartyAction) {
        when (action) {
            is MyPartyAction.OnSelectTab -> _myPartyState.update { it.copy(selectedTabText = action.selectedTabText) }
            is MyPartyAction.OnOrderByChange -> {
                _myPartyState.update { currentState ->
                    val sortedList = if (action.orderByDesc) {
                        currentState.myPartyList.partyUsers.sortedByDescending { it.createdAt }
                    } else {
                        currentState.myPartyList.partyUsers.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        orderByDesc = action.orderByDesc,
                        myPartyList = currentState.myPartyList.copy(partyUsers = sortedList)
                    )
                }
            }

            is MyPartyAction.OnSelectRecruitmentTab -> {
                _myPartyState.update { it.copy(selectedRecruitmentStatus = action.selectedRecruitmentTabText) }
            }

            is MyPartyAction.OnRecruitmentOrderByChange -> {
                _myPartyState.update { currentState ->
                    val sortedList = if (action.orderByRecruitmentDateDesc) {
                        currentState.myRecruitmentList.partyApplications.sortedByDescending { it.createdAt }
                    } else {
                        currentState.myRecruitmentList.partyApplications.sortedBy { it.createdAt }
                    }
                    currentState.copy(
                        orderByRecruitmentDateDesc = action.orderByRecruitmentDateDesc,
                        myRecruitmentList = currentState.myRecruitmentList.copy(partyApplications = sortedList)
                    )
                }
            }

            is MyPartyAction.OnShowHelpCard -> _myPartyState.update { it.copy(isShowHelpCard = action.isShowHelpCard) }
            is MyPartyAction.OnSelectStatus -> _myPartyState.update { currentState ->
                currentState.copy(
                    selectedStatus = action.selectedStatus
                )
            }

            is MyPartyAction.OnCancelRecruitment -> {} //cancelRecruitment(partyId = action.partyId, partyApplicationId = action.partyApplicationId)
            is MyPartyAction.OnApprovalParty -> {} //approvalParty(partyId = action.partyId, partyApplicationId = action.partyApplicationId)
            is MyPartyAction.OnRejectionParty -> {}  //rejectionParty(partyId = action.partyId, partyApplicationId = action.partyApplicationId)
        }
    }
}