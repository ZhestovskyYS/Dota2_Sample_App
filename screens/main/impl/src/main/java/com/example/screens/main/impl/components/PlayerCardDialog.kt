package com.example.screens.main.impl.components

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import com.example.screens.main.api.data.Player
import com.example.screens.main.api.data.PlayerInfoShort
import com.example.utils.R
import com.example.utils.ui.PlayerInfoCard

@Composable
internal fun PlayerCardDialog(
    modifier: Modifier = Modifier,
    player: PlayerInfoShort,
    onDismissRequest: () -> Unit,
    onProfileLinkIsClicked: (url: String) -> Unit,
) {

    Dialog(onDismissRequest) {
        PlayerInfoCard(
            modifier = modifier,
            nickname = player.nickname,
            hasDotaPlus = player.hasDotaPlus,
            lastOnline = player.lastOnline,
            avatarImagePainter = chooseAvatar(player.avatar),
            onProfileLinkIsClicked = { onProfileLinkIsClicked(player.profileLink) },
            onSteamProfileLinkIsClicked = { onProfileLinkIsClicked(player.steamProfileLink) }
        )
    }
}

@Composable
private fun chooseAvatar(playerAvatar: Bitmap?) =
    if (playerAvatar != null) BitmapPainter(playerAvatar.asImageBitmap())
    else painterResource(id = R.drawable.dota2_icon_placeholder)