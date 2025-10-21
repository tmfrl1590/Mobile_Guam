package com.party.guham2.presentation.screens.main.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MainEvent {
    private val _goToPartyCreate = MutableSharedFlow<Unit>(replay = 1)
    val goToPartyCreate: SharedFlow<Unit> = _goToPartyCreate.asSharedFlow()

    fun gotoPartyCreate(){
        _goToPartyCreate.tryEmit(Unit)
    }
}