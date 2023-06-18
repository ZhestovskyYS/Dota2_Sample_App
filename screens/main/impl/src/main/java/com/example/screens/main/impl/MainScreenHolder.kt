package com.example.screens.main.impl

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.screens.main.api.state.MainScreenState

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
fun MainScreenHolder(
    viewModel: MainScreenViewModel
) {
    val screenState by viewModel.state.collectAsState()

    when (val state = screenState) {
        is MainScreenState.EmptyList ->
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                players = emptyList(),
            )

        is MainScreenState.Initiated ->
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                players = state.players,
            )
    }

    LaunchedEffect(Unit) {
        viewModel.searchPlayers("")
    }
}