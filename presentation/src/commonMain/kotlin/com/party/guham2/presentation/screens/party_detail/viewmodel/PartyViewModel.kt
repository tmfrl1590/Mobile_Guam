package com.party.guham2.presentation.screens.party_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.model.party.toPresentation
import com.party.guham2.presentation.screens.party_detail.action.PartyDetailAction
import com.party.guham2.presentation.screens.party_detail.state.PartyDetailState
import com.party.guham2.usecase.party.GetPartyDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PartyDetailViewModel(
    private val getPartyDetailUseCase: GetPartyDetailUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(PartyDetailState())
    val state = _state.asStateFlow()

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

    fun onAction(action: PartyDetailAction) {
        when(action){
            is PartyDetailAction.OnClickTab -> _state.update { it.copy(selectedTabText = action.tabText) }
        }
    }
}