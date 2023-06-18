package com.example.screens.player.impl.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.screens.player.impl.R

@Composable
internal fun PlayerBestHeroOutlinedCard(
    modifier: Modifier = Modifier,
    heroImagePainter: Painter,
    heroName: String,
) {
    OutlinedCard(modifier = modifier) {
        Box {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                text = "Most played hero",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = heroImagePainter,
                contentDescription = "Hero image"
            )
        }

        Box {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.hero_name),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = heroName,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
@Preview
private fun PlayerBestHeroOutlinedCard_Preview() {
    PlayerBestHeroOutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        heroImagePainter = painterResource(id = com.example.utils.R.drawable.dota2_icon_placeholder),
        heroName = "Abbadon"
    )
}