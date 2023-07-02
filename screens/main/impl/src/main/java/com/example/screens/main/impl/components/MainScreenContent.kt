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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.impl.R
import com.example.screens.main.api.data.Player
import com.example.screens.main.impl.MainScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
internal fun MainScreenContent(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    searchFieldValue: TextFieldValue,
    pullRefreshState: PullRefreshState,
    players: List<Player>,
    lazyColumnState: LazyListState,
    @DrawableRes
    placeHolderDrawableRes: Int,
    isInitialState: Boolean,
    isRefreshing: Boolean,
    isFabVisible: Boolean,
    onFabIsClicked: () -> Unit,
    onSearchTextIsChanged: (TextFieldValue) -> Unit,
    onCardIsClicked: (Player) -> Unit,
    onCardLongClick: (Player) -> Unit,
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
                searchFieldValue = searchFieldValue,
                onSearchFieldValueChange = onSearchTextIsChanged
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible,
                enter = fadeIn(initialAlpha = 0.3f),
                exit = fadeOut(),
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
                        placeHolderDrawableRes = placeHolderDrawableRes,
                        onCardIsClicked = onCardIsClicked,
                        onCardLongClick = onCardLongClick,
                    )
                } else {
                    EmptyListPlaceholder(
                        modifier = Modifier.fillMaxSize(),
                        message = if (isInitialState) "No players found"
                        else "Input player's nick to search"
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

@Composable
@Preview(
    showSystemUi = true,
    device = "id:pixel_6a",
    group = "MainScreenPreview",
    name = "EmptyList"
)
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun MainScreenPreview1() {
    val players = listOf<Player>()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val lazyColumnState = rememberLazyListState()

    val firstVisibleItemIndex by remember {
        derivedStateOf { lazyColumnState.firstVisibleItemIndex }
    }

    val textFieldValue = remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    var isRefreshing by remember { mutableStateOf(false) }
    var isFabVisible by remember { mutableStateOf(true) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(500L)
                isRefreshing = false
            }
        }
    )

    MaterialTheme {
        MainScreenContent(
            modifier = Modifier.fillMaxSize(),
            players = players,
            scaffoldState = scaffoldState,
            searchFieldValue = textFieldValue.value,
            pullRefreshState = pullRefreshState,
            lazyColumnState = lazyColumnState,
            placeHolderDrawableRes = R.drawable.dota2_logo_icon,
            isInitialState = true,
            isRefreshing = isRefreshing,
            isFabVisible = isFabVisible,
            onFabIsClicked = {
                coroutineScope.launch {
                    lazyColumnState.animateScrollToItem(0)
                    isFabVisible = false
                }
            },
            onSearchTextIsChanged = { textFieldValue.value = it },
            onCardIsClicked = {},
            onCardLongClick = {}
        )
    }

    LaunchedEffect(firstVisibleItemIndex) { isFabVisible = firstVisibleItemIndex >= 3 }
}

@Composable
@Preview(
    showSystemUi = true,
    device = "id:pixel_6a",
    group = "MainScreenPreview",
    name = "NotEmptyList"
)
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalFoundationApi
fun MainScreenPreview2() {
    val players = listOf(
        Player(
            id = "123",
            nickname = "Kostya",
            avatar = ""
        ),
        Player(
            id = "1234",
            nickname = "Leha",
            avatar = ""
        ),
        Player(
            id = "12345",
            nickname = "Yarik",
            avatar = ""
        ),
        Player(
            id = "123456",
            nickname = "KonstAntin",
            avatar = ""
        ),
        Player(
            id = "123567",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "12358",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "123569",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235691",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235692",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235693",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235694",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235695",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235696",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235697",
            nickname = "Alex",
            avatar = ""
        ),
        Player(
            id = "1235698",
            nickname = "Alex",
            avatar = ""
        )
    )
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val lazyColumnState = rememberLazyListState()

    val firstVisibleItemIndex by remember {
        derivedStateOf { lazyColumnState.firstVisibleItemIndex }
    }

    val textFieldValue = remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    var isRefreshing by remember { mutableStateOf(false) }
    var isFabVisible by remember { mutableStateOf(true) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(500L)
                isRefreshing = false
            }
        }
    )

    MaterialTheme {
        MainScreenContent(
            modifier = Modifier.fillMaxSize(),
            players = players,
            scaffoldState = scaffoldState,
            searchFieldValue = textFieldValue.value,
            pullRefreshState = pullRefreshState,
            lazyColumnState = lazyColumnState,
            placeHolderDrawableRes = R.drawable.dota2_logo_icon,
            isInitialState = false,
            isRefreshing = isRefreshing,
            isFabVisible = isFabVisible,
            onFabIsClicked = {
                coroutineScope.launch {
                    lazyColumnState.animateScrollToItem(0)
                    isFabVisible = false
                }
            },
            onSearchTextIsChanged = { textFieldValue.value = it },
            onCardIsClicked = {},
            onCardLongClick = {}
        )
    }

    LaunchedEffect(firstVisibleItemIndex) { isFabVisible = firstVisibleItemIndex >= 3 }
}
