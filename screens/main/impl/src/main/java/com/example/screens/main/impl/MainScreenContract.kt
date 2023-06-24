package com.example.screens.main.impl

import androidx.compose.ui.text.input.TextFieldValue
import com.example.screens.main.api.data.Player
import com.example.utils.mvi.UnidirectionalViewModel

interface MainScreenContract : UnidirectionalViewModel<
        MainScreenContract.State,
        MainScreenContract.Event,
        MainScreenContract.Effect
        > {

    data class State(
        val isInitialState: Boolean = true,
        val isLoading: Boolean = false,
        val isFabVisible: Boolean = false,
        val players: List<Player> = emptyList(),
        val searchPattern: TextFieldValue = TextFieldValue(""),
        /**
         *  To use with Toast.makeText(context, "message", Toast.Short).show(), SnackBar
         */
        val errorText: String? = null,
    )

    sealed interface Event {
        data class SearchPatternInput(val pattern: TextFieldValue) : Event
        data class PlayerCardWasClicked(val player: Player) : Event
        data class PlayerCardWasLongClicked(val player: Player) : Event
        object RefreshList : Event
        object ListIsOnTop: Event
        object ListWasOverScrolled : Event
        object FabWasClicked : Event
    }

    sealed interface Effect {
        data class ShowPlayerCardDialog(val player: Player) : Effect
        data class NavigateToPlayerScreen(val player: Player) : Effect
        object ScrollListToTheTop : Effect
    }
}