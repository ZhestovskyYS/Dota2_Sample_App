package com.example.screens.player.feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.screens.player.impl.PlayerInfoScreenHolder
import com.example.utils.ScreenNavPoint

object PlayerScreenNavPoint: ScreenNavPoint {
    override val link = "PlayerScreen"
    override var argument: String? = null

    override val ui: @Composable (modifier: Modifier) -> Unit = { modifier ->
        PlayerInfoScreenHolder(modifier, profileId = argument)
    }
}