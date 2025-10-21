package com.party.guham2.presentation.screens.active.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ActiveEvent {
    private val _scrollToUp = MutableSharedFlow<Unit>(replay = 1)
    val scrollToUp = _scrollToUp.asSharedFlow()

    fun scrollToUp(){
        _scrollToUp.tryEmit(Unit)
    }
}