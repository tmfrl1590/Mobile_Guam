package com.party.guham2.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.party.guham2.design.WHITE

@Composable
fun ProfileScreenRoute(){
    ProfileScreen()
}

@Composable
private fun ProfileScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WHITE)
            .padding(horizontal = 20.dp)
    ) {

    }
}