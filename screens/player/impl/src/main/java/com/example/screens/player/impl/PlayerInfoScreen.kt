package com.example.screens.player.impl

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.screens.player.impl.components.PlayerInfoScreenContent
import com.example.utils.mvi.collectInLaunchedEffect
import com.example.utils.mvi.use

@Composable
fun PlayerInfoScreen(
    viewModel: PlayerInfoScreenViewModel,
) {
    val (state, event, effect) = use(viewModel)

    val context = LocalContext.current
    val error by remember {
        derivedStateOf { state.errorText }
    }

    effect.collectInLaunchedEffect { incomingEffect ->
        when (incomingEffect) {
            is PlayerScreenContract.Effect.OpenLink -> incomingEffect.run {
                context.open()
            }
        }
    }

    error?.let { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    if (state.playerInfo == null) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        }
    } else {
        PlayerInfoScreenContent(
            modifier = Modifier.padding(horizontal = 16.dp),
            player = state.playerInfo,
            onProfileLinkButtonIsClicked = { url ->
                event(
                    PlayerScreenContract.Event.PlayerProfileButtonWasClicked(url)
                )
            }
        )
    }
}
