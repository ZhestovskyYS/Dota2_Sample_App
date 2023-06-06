package com.example.screens.main.impl

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.impl.R
import kotlinx.coroutines.launch

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

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


@Composable
@Preview(showSystemUi = true, device = "id:pixel_6a")
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}
