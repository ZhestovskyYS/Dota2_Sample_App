package com.example.screens.player.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.ProcessState
import com.example.screens.main.api.usecases.GetPlayerInfoUseCase
import com.example.screens.player.impl.usecases.GetPlayerInfoUseCaseImpl
import com.example.utils.OpenLinkUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerInfoScreenViewModel(
    private val playerId: String,
    private val getPlayerInfoUseCase: GetPlayerInfoUseCase = GetPlayerInfoUseCaseImpl(),
    private val openLinkUseCase: OpenLinkUseCase = OpenLinkUseCase(),
) : ViewModel(), PlayerScreenContract {

    private val _state = MutableStateFlow(PlayerScreenContract.State())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlayerScreenContract.Effect>()
    override val effect = _effect.asSharedFlow()

    override fun event(event: PlayerScreenContract.Event) {
        when (event) {
            is PlayerScreenContract.Event.PlayerProfileButtonWasClicked -> viewModelScope.launch {
                _effect.emit(
                    PlayerScreenContract.Effect.OpenLink {
                        openLinkUseCase.run {
                            invoke(event.url)
                        }
                    }
                )
            }
        }
    }

    init {
        fetchPlayerInfo()
    }

    private fun fetchPlayerInfo() {
        viewModelScope.launch {
            when (val response = getPlayerInfoUseCase(playerId)) {
                is ProcessState.Error -> _state.update {
                    it.copy(errorText = response.throwable.localizedMessage)
                }

                is ProcessState.Success -> _state.update {
                    it.copy(playerInfo = response.result)
                }
            }
        }
    }
}