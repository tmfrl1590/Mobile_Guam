package com.party.guham2.presentation.screens.recruitment_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.model.recruitment.toPresentation
import com.party.guham2.presentation.screens.recruitment_detail.state.RecruitmentDetailState
import com.party.guham2.usecase.recruitment.GetRecruitmentDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecruitmentDetailViewModel(
    private val getRecruitmentDetailUseCase: GetRecruitmentDetailUseCase
): ViewModel() {
    private val _recruitmentDetailState = MutableStateFlow(RecruitmentDetailState())
    val recruitmentDetailState = _recruitmentDetailState.asStateFlow()

    fun getRecruitmentDetail(partyRecruitmentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _recruitmentDetailState.update { it.copy(isLoading = true) }

            getRecruitmentDetailUseCase(
                partyRecruitmentId = partyRecruitmentId
            )
                .onSuccess { result ->
                    _recruitmentDetailState.update { it.copy(isLoading = false, recruitmentDetail = result.toPresentation()) }
                }
                .onError {
                    _recruitmentDetailState.update { it.copy(isLoading = false) }
                }
        }
    }
}