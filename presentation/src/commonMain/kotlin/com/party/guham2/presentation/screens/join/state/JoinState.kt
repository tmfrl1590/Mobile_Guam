package com.party.guham2.presentation.screens.join.state

data class JoinState(
    val isShowCancelJoinDialog: Boolean = false,

    // 사용자가 입력한 닉네임
    val userNickName: String = "",

    // 닉네임 warning
    val warningMessage: String = "",
)
