package com.example.screens.main.impl.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.screens.main.api.data.Player

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
internal fun MainScreenContent(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    searchFieldValue: MutableState<TextFieldValue>,
    pullRefreshState: PullRefreshState,
    players: List<Player>,
    lazyColumnState: LazyListState,
    @DrawableRes
    placeHolderDrawableRes: Int,
    isRefreshing: Boolean
) {
    Scaffold(
        modifier,
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                searchFieldValue = searchFieldValue.value,
                onSearchFieldValueChange = { newValue -> searchFieldValue.value = newValue }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.consumeWindowInsets(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                if (players.isNotEmpty()) {
                    PlayersList(
                        lazyColumnState = lazyColumnState,
                        players = players,
                        placeHolderDrawableRes = placeHolderDrawableRes
                    )
                } else {
                    EmptyListPlaceholder(
                        modifier = Modifier.fillMaxSize(),
                        message = "No players found"
                    )
                }

                PullRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                )
            }
        }
    }
}