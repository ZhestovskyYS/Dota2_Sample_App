package com.example.screens.main.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.screens.main.api.data.Player

@ExperimentalMaterial3Api
@Composable
internal fun PlayersList(
    modifier: Modifier = Modifier,
    lazyColumnState: LazyListState,
    players: List<Player>,
    placeHolderDrawableRes: Int
) {
    LazyColumn(
        modifier = modifier,
        state = lazyColumnState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            horizontal = 12.dp, vertical = 18.dp
        )
    ) {
        items(
            players.size,
            key = { index -> players[index].id }
        ) { index ->
            PlayerCard(
                player = players[index],
                placeHolderDrawableRes = placeHolderDrawableRes
            )

            if (index != players.lastIndex)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
        }
    }
}