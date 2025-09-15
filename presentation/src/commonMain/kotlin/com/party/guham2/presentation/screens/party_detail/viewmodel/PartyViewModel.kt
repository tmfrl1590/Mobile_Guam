package com.party.guham2.presentation.screens.party_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.model.party.toPresentation
import com.party.guham2.presentation.screens.party_detail.action.PartyDetailAction
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState
import com.party.guham2.usecase.party.GetPartyDetailUseCase
import com.party.guham2.usecase.party.GetPartyUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PartyDetailViewModel(
    private val getPartyDetailUseCase: GetPartyDetailUseCase,
    private val getPartyUsersUseCase: GetPartyUsersUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(PartyDetailState())
    val state = _state.asStateFlow()

    // 파티 상세 정보 조회
    fun getPartyDetail(partyId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getPartyDetailUseCase(
                partyId = partyId
            )
                .onSuccess { result ->
                    _state.update { it.copy(partyDetail = result.toPresentation()) }
                }
                .onError {  }
        }
    }

    // 파티에 속한 유저 리스트 조회
    fun getPartyUsers(partyId: Int, page: Int, limit: Int, sort: String, order: String){
        viewModelScope.launch(Dispatchers.IO) {
            getPartyUsersUseCase(
                partyId = partyId,
                page = page,
                limit = limit,
                sort = sort,
                order = order
            )
                .onSuccess { result ->
                    _state.update { it.copy(partyUsers = result.toPresentation()) }
                }

        }
    }

    fun onAction(action: PartyDetailAction) {
        when(action){
            is PartyDetailAction.OnClickTab -> _state.update { it.copy(selectedTabText = action.tabText) }
        }
    }
}