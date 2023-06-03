package com.example.screens.main.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.search.SearchBar

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier,
        topBar = {
            AppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
            )
        },

    ) { paddingValues ->
        Surface(
            modifier = Modifier.consumeWindowInsets(paddingValues)
        ) {

        }
    }
}

@Composable
private fun AppBar(modifier: Modifier = Modifier) {
    Row(modifier) {

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
