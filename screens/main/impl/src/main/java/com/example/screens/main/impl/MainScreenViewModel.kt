package com.example.screens.main.impl

import androidx.lifecycle.ViewModel
import com.example.screens.main.api.state.MainScreenState
import com.example.screens.main.api.usecases.GetPlayersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainScreenState>(
        MainScreenState.EmptyList
    )
    val state = _state.asStateFlow()


    fun searchPlayers(searchPattern: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getPlayersUseCase(searchPattern)
            if (result.isEmpty()) {
                _state.value = MainScreenState.EmptyList
            } else {
                _state.value = MainScreenState.Initiated(players = result)
            }
        }
    }
}