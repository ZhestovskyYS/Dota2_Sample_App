package com.example.screens.player.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screens.player.impl.components.PlayerBestHeroOutlinedCard
import com.example.screens.player.impl.components.PlayerStatsOutlinedCard
import com.example.utils.ui.PlayerInfoCard
import com.example.utils.R

@Composable
fun PlayerInfoScreen(modifier: Modifier = Modifier) {
    Surface(modifier) {
        LazyColumn(verticalArrangement = Arrangement.Top) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                PlayerInfoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    nickname = "Durachyo",
                    lastOnline = "12 hours ago",
                    hasDotaPlus = false,
                    avatarImagePainter = painterResource(id = R.drawable.dota2_icon_placeholder),
                    onSteamProfileLinkIsClicked = { /*TODO*/ },
                    onProfileLinkIsClicked = { /*TODO*/ },
                )
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))

                PlayerStatsOutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    mmr = "5250",
                    wins = "778",
                    losses = "678",
                    winRate = "66,78",
                )
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))

                PlayerBestHeroOutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    heroImagePainter = painterResource(id = R.drawable.dota2_icon_placeholder),
                    heroName = "Abbadon"
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PlayerInfoScreen_Preview() {
    MaterialTheme {
        PlayerInfoScreen(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 14.dp)
        )
    }
}