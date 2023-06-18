package com.example.screens.player.impl.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screens.player.impl.R

@Composable
internal fun PlayerStatsOutlinedCard(
    modifier: Modifier = Modifier,
    mmr: String,
    wins: String,
    losses: String,
    winRate: String,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
                    .widthIn(max = 200.dp),
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.mmr),
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.bodyMedium,
                text = mmr
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
                    .widthIn(max = 200.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                text = stringResource(R.string.wins),
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.bodyMedium,
                text = wins
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
                    .widthIn(max = 200.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                text = stringResource(R.string.losses),
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.bodyMedium,
                text = losses
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
                    .widthIn(max = 200.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                text = stringResource(R.string.win_rate),
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.bodyMedium,
                text = winRate
            )
        }
    }
}

@Composable
@Preview
private fun PlayerStatsOutlinedCard_Preview() {
    PlayerStatsOutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        mmr = "2500",
        wins = "776",
        losses = "678",
        winRate = "66,87"
    )
}