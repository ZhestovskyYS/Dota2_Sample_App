package com.example.screens.main.impl.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.impl.R
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
    isRefreshing: Boolean,
    isFabVisible: Boolean,
    onFabIsClicked: () -> Unit,
) {
    val localDensity = LocalDensity.current

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
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible,
                enter = slideInVertically { with(localDensity) { -40.dp.roundToPx() } }
                        + expandVertically(expandFrom = Alignment.Top)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically()
                        + shrinkVertically()
                        + fadeOut(),
            ) {
                FloatingActionButton(
                    modifier = Modifier.size(56.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    elevation = FloatingActionButtonDefaults.elevation(),
                    shape = RoundedCornerShape(16.dp),
                    onClick = onFabIsClicked,
                ) {
                    Icon(
                        modifier = Modifier.wrapContentSize(),
                        painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                        contentDescription = "Scroll to the top"
                    )
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End,
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