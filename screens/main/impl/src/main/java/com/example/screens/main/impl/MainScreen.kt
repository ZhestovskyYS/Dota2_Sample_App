package com.example.screens.main.impl

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impl.R
import com.example.screens.main.api.data.Player
import kotlinx.coroutines.launch

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnState = rememberLazyListState()
    val players = listOf(
        Player(
            id = "123",
            nickname = "Kostya",
            avatar = LocalContext.current.getDrawable(R.drawable.dota2_logo_icon)
        ),
        Player(
            id = "1234",
            nickname = "Leha",
            avatar = LocalContext.current.getDrawable(R.drawable.dota2_logo_icon)
        ),
        Player(
            id = "12345",
            nickname = "Yarik",
            avatar = LocalContext.current.getDrawable(R.drawable.dota2_logo_icon)
        ),
        Player(
            id = "123456",
            nickname = "KonstAntin",
            avatar = LocalContext.current.getDrawable(R.drawable.dota2_logo_icon)
        ),
        Player(
            id = "123567",
            nickname = "Alex",
            avatar = LocalContext.current.getDrawable(R.drawable.dota2_logo_icon)
        )
    )

    Scaffold(
        modifier,
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onBurgerIconIsClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent()
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier.consumeWindowInsets(paddingValues)
        ) {
            LazyColumn(
                state = lazyColumnState,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    horizontal = 12.dp, vertical = 18.dp
                )
            ) {
//                items(
//                    players.size,
//                    key = { index -> players[index].id }
//                ) { index ->
//
//                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    searchFieldValue: String = "",
    onSearchFieldValueChange: (String) -> Unit = { },
    onBurgerIconIsClicked: () -> Unit = { },
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(percent = 50)
                )
                .padding(4.dp),
            shape = RoundedCornerShape(percent = 50),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(percent = 50))
                        .clickable(onClick = onBurgerIconIsClicked),
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Burger icon"
                )
            },
            value = searchFieldValue,
            onValueChange = onSearchFieldValueChange,
        )
    }


}

@ExperimentalMaterial3Api
@Composable
private fun DrawerContent(
    onItemClicked: (String) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dota2_logo_icon),
            contentDescription = "dota2 logo"
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PlayerCard(
    //player: Player,
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit = {},
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(size = 12.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
        onClick = { onClick("") },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.dota2_logo_icon),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = "Nickname",
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
@ExperimentalMaterial3Api
fun PlayerCardPreview() {
    MaterialTheme {
        PlayerCard(
            modifier = Modifier
                .width(335.dp)
                .height(80.dp)
        )
    }
}


@Composable
@Preview(showSystemUi = true, device = "id:pixel_6a")
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}
