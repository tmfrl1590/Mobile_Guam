package com.party.guham2.presentation.screens.active

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.WHITE
import com.party.guham2.presentation.screens.active.event.ActiveEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ActiveScreenRoute(
    onStartScroll: (Boolean) -> Unit,
){

    val listState = rememberLazyListState()
    val isFabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    onStartScroll(isFabVisible)

    LaunchedEffect(Unit) {
        ActiveEvent.scrollToUp.collectLatest {
            listState.animateScrollToItem(0)
        }
    }


    ActiveScreen()
}

@Composable
private fun ActiveScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WHITE)
            .padding(horizontal = 20.dp)
    ) {

    }
}