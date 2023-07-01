package com.example.screens.player.impl

import android.content.Context
import com.example.screens.main.api.data.PlayerInfo
import com.example.utils.mvi.UnidirectionalViewModel

interface PlayerScreenContract : UnidirectionalViewModel<
        PlayerScreenContract.State,
        PlayerScreenContract.Event,
        PlayerScreenContract.Effect
        > {

    data class State(
        val playerInfo: PlayerInfo? = null,
        val isLoading: Boolean = true,
        val errorText: String? = null
    )

    sealed interface Event {
        data class PlayerProfileButtonWasClicked(val url: String): Event
    }

    sealed interface Effect {
        class OpenLink(val open: Context.() -> Unit): Effect
    }
}