package com.example.screens.main.impl

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.ProcessState
import com.example.screens.main.api.data.Player
import com.example.screens.main.api.usecases.GetPlayerInfoShortUseCase
import com.example.screens.main.api.usecases.GetPlayersUseCase
import com.example.screens.main.impl.usecases.GetPlayerUseCaseImpl
import com.example.screens.player.feature.GetPlayerInfoShortUseCaseImpl
import com.example.utils.OpenLinkUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getPlayersUseCase: GetPlayersUseCase = GetPlayerUseCaseImpl(),
    private val getPlayerShortInfoUseCase: GetPlayerInfoShortUseCase = GetPlayerInfoShortUseCaseImpl(),
    private val openLinkUseCase: OpenLinkUseCase = OpenLinkUseCase(),
) : ViewModel(), MainScreenContract {

    private val _state = MutableStateFlow(MainScreenContract.State())
    override val state = _state.asStateFlow()

    private var lastSavedPattern = ""
    private var wasSearchedAlready = false

    private val _effect = MutableSharedFlow<MainScreenContract.Effect>()
    override val effect = _effect.asSharedFlow()

    override fun event(event: MainScreenContract.Event) {
        when (event) {
            is MainScreenContract.Event.PlayerCardWasClicked ->
                viewModelScope.launch {
                    _effect.emit(MainScreenContract.Effect.NavigateToPlayerScreen(event.player))
                }

            is MainScreenContract.Event.PlayerCardWasLongClicked ->
                viewModelScope.launch {
                    when (val response = getPlayerShortInfoUseCase(event.player.id)) {
                        is ProcessState.Error -> _state.update {
                            it.copy(errorText = response.throwable.localizedMessage)
                        }

                        is ProcessState.Success -> _effect.emit(
                            MainScreenContract.Effect.ShowPlayerCardDialog(response.result)
                        )
                    }
                }

            is MainScreenContract.Event.SearchPatternInput -> _state.update { currentState ->
                currentState.copy(searchPattern = event.pattern)
            }

            MainScreenContract.Event.RefreshList -> {
                checkPatternAndDo(
                    pattern = _state.value.searchPattern.text,
                    action = { pattern ->
                        _state.update { currentState ->
                            currentState.copy(isLoading = true)
                        }
                        searchPlayers(pattern)
                    },
                    errorAction = {
                        viewModelScope.launch {
                            _state.update { currentState ->
                                currentState.copy(isLoading = true)
                            }
                            delay(200L)
                            _state.update { currentState ->
                                currentState.copy(isLoading = false)
                            }
                        }
                    }
                )
            }

            MainScreenContract.Event.ListWasOverScrolled -> {
                _state.update { currentState ->
                    currentState.copy(isFabVisible = true)
                }
            }

            MainScreenContract.Event.FabWasClicked -> viewModelScope.launch {
                _effect.emit(MainScreenContract.Effect.ScrollListToTheTop)
                _state.update { currentState ->
                    currentState.copy(isFabVisible = false)
                }
            }

            MainScreenContract.Event.ListIsOnTop -> _state.update { currentState ->
                currentState.copy(isFabVisible = false)
            }

            is MainScreenContract.Event.PlayerProfileButtonClicked-> viewModelScope.launch {
                _effect.emit(
                    MainScreenContract.Effect.OpenLink {
                        openLink(event.url)
                    }
                )
            }
        }
    }

    private val searchingFlow = _state
        .debounce(500L)
        .onEach {
            checkPatternAndDo(
                pattern = it.searchPattern.text,
                action = { pattern ->
                    _state.update { currentState ->
                        currentState.copy(isLoading = true)
                    }
                    searchPlayers(pattern)
                }
            )

            lastSavedPattern = it.searchPattern.text
        }

    private fun checkPatternAndDo(
        pattern: String,
        action: (String) -> Unit,
        errorAction: () -> Unit = { },
    ) {
        if (pattern.isNotBlank() && lastSavedPattern != pattern)
            action(pattern)
        else
            errorAction()
    }

    init {
        searchingFlow.launchIn(viewModelScope)
    }

    private fun searchPlayers(searchPattern: String) {
        fun onSuccess(players: List<Player>) {
            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    players = players
                )
            }
        }

        fun onError(error: Throwable) = with(error) {
            printStackTrace()
            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    errorText = localizedMessage
                )
            }
        }

        if (!wasSearchedAlready) {
            wasSearchedAlready = true
            _state.update {
                it.copy(isInitialState = false)
            }
        }
        viewModelScope.launch {
            when (val response = getPlayersUseCase(searchPattern)) {
                is ProcessState.Error -> onError(response.throwable)
                is ProcessState.Success -> onSuccess(response.result)
            }
        }
    }

    private fun Context.openLink(url: String) = with(openLinkUseCase) {
        invoke(url)
    }
}