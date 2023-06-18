package com.example.screens.main.impl

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.impl.R
import com.example.screens.main.api.data.Player
import com.example.screens.main.impl.components.MainScreenContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
fun MainScreen(
    modifier: Modifier = Modifier,
    players: List<Player>,
    @DrawableRes
    placeHolderDrawableRes: Int = R.drawable.dota2_logo_icon
) {
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

    MainScreenContent(
        modifier = modifier,
        scaffoldState = scaffoldState,
        searchFieldValue = textFieldValue,
        pullRefreshState = pullRefreshState,
        players = players,
        lazyColumnState = lazyColumnState,
        placeHolderDrawableRes = placeHolderDrawableRes,
        isRefreshing = isRefreshing,
        isFabVisible = isFabVisible,
        onFabIsClicked = {
            coroutineScope.launch {
                lazyColumnState.animateScrollToItem(0)
                isFabVisible = false
            }
        }
    )

    LaunchedEffect(firstVisibleItemIndex) { isFabVisible = firstVisibleItemIndex >= 3 }
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
fun MainScreenPreview1() {
    MaterialTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            players = listOf()
        )
    }
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
fun MainScreenPreview2() {
    val players = listOf(
        Player(
            id = "123",
            nickname = "Kostya",
            avatar = null
        ),
        Player(
            id = "1234",
            nickname = "Leha",
            avatar = null
        ),
        Player(
            id = "12345",
            nickname = "Yarik",
            avatar = null
        ),
        Player(
            id = "123456",
            nickname = "KonstAntin",
            avatar = null
        ),
        Player(
            id = "123567",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "12358",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "123569",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235691",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235692",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235693",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235694",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235695",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235696",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235697",
            nickname = "Alex",
            avatar = null
        ),
        Player(
            id = "1235698",
            nickname = "Alex",
            avatar = null
        )
    )

    MaterialTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            players = players
        )
    }
}
