package com.party.guham2.presentation.screens.join.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.party.guham2.core.domain.onError
import com.party.guham2.core.domain.onSuccess
import com.party.guham2.presentation.screens.join.action.JoinAction
import com.party.guham2.presentation.screens.join.state.JoinState
import com.party.guham2.usecase.user.join.CheckUserNickNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JoinViewModel(
    private val checkUserNickNameUseCase: CheckUserNickNameUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(JoinState())
    val state = _state.asStateFlow()

    private val _goToBirthDayScreen = MutableSharedFlow<Unit>()
    val goToBirthDayScreen = _goToBirthDayScreen.asSharedFlow()


    fun checkUserNickName(
        signupAccessToken: String,
        nickname: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            checkUserNickNameUseCase(
                signupAccessToken = signupAccessToken,
                nickname = nickname,
            )
                .onSuccess {
                    _goToBirthDayScreen.emit(Unit)
                }
                .onError {
                    // 중복이면 warningMessage 세팅
                }
        }
    }

    fun onAction(action: JoinAction){
        when(action){
            is JoinAction.OnShowCancelJoinDialog -> _state.update { it.copy(isShowCancelJoinDialog = action.isShow) }
            is JoinAction.OnInputNickName ->{
                _state.update {
                    it.copy(
                        userNickName = action.nickName,
                        warningMessage = convertWarningText(action.nickName),
                    )
                }
            }
            is JoinAction.OnResetNickName -> _state.update { it.copy(userNickName = "", warningMessage = "") }
            is JoinAction.OnCheckNickName -> {
                checkUserNickName(
                    signupAccessToken = "",
                    nickname = _state.value.userNickName
                )
            }
        }
    }
}


fun convertWarningText(userNickName: String): String{
    return if(containsSpecialCharacters(userNickName)) "특수문자는 사용할 수 없어요."
    else if(userNickName.isEmpty()) ""
    else if(userNickName.length > 15 || userNickName.length < 2) "닉네임은 2자 이상 15자 이내로 입력해주세요"
    else ""
}

fun containsSpecialCharacters(input: String): Boolean {
    // 한글, 알파벳, 숫자를 제외한 특수문자를 확인하기 위한 정규식
    val regex = Regex("[^가-힣a-zA-Z0-9]") // 한글, 알파벳, 숫자가 아닌 문자를 찾음
    return regex.containsMatchIn(input)
}