package com.example.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerInfoCard(
    modifier: Modifier = Modifier,
    nickname: String,
    lastOnline: String,
    avatarImagePainter: Painter,
    onProfileLinkIsClicked: () -> Unit,
    onSteamProfileLinkIsClicked: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        border = CardDefaults.outlinedCardBorder(enabled = true),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarImage(
                painter = avatarImagePainter,
                hasDotaPlus = true
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = stringResource(R.string.nickname),
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = nickname,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = stringResource(R.string.last_online),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = lastOnline,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                onClick = onProfileLinkIsClicked,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = stringResource(R.string.profile_link)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = onSteamProfileLinkIsClicked,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = stringResource(R.string.steam_profile_link)
                )
            }
        }
    }
}

@Composable
@Preview
private fun PlayerCard_Preview() {
    PlayerInfoCard(
        avatarImagePainter = painterResource(id = R.drawable.dota2_icon_placeholder),
        nickname = "Durachyo",
        lastOnline = "12 hours ago",
        onProfileLinkIsClicked = {},
        onSteamProfileLinkIsClicked = {},
    )
}



