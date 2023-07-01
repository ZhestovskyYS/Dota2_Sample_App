package com.example.screens.main.impl

import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import com.example.screens.main.api.data.Player
import com.example.screens.main.api.data.PlayerInfoShort
import com.example.utils.mvi.UnidirectionalViewModel

interface MainScreenContract : UnidirectionalViewModel<
        MainScreenContract.State,
        MainScreenContract.Event,
        MainScreenContract.Effect
        > {

    data class State(
        val isInitialState: Boolean = true,
        val isLoading: Boolean = false,
        val isPlayerInfoLoading: Boolean = false,
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
        data class PlayerProfileButtonClicked(val url: String): Event
        object RefreshList : Event
        object ListIsOnTop: Event
        object ListWasOverScrolled : Event
        object FabWasClicked : Event
    }

    sealed interface Effect {
        data class ShowPlayerCardDialog(val player: PlayerInfoShort) : Effect
        data class NavigateToPlayerScreen(val player: Player) : Effect
        object ScrollListToTheTop : Effect
        class OpenLink(val open: Context.() -> Unit): Effect
    }
}