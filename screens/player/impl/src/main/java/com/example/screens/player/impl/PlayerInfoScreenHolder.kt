package com.example.screens.player.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.screens.player.impl.components.ErrorPlaceholder

@Composable
fun PlayerInfoScreenHolder(
    modifier: Modifier = Modifier,
    profileId: String?
) {

    Surface(modifier) {
        if (profileId == null) {
            ErrorPlaceholder(
                modifier = Modifier.fillMaxSize(),
                message = "Error on getting profile id!"
            )
        } else {
            PlayerInfoScreen(
                viewModel = PlayerInfoScreenViewModel(playerId = profileId)
            )
        }
    }
}