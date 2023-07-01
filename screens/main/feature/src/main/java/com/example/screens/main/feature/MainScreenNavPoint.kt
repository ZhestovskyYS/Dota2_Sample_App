package com.example.screens.main.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.screens.main.impl.MainScreenContract
import com.example.screens.main.impl.MainScreenHolder
import com.example.utils.ScreenNavPoint

class MainScreenNavPoint(
    private val navigateToPlayerScreen: @Composable (profileId: String) -> Unit
) : ScreenNavPoint {
    override val link = "MainScreen"
    override var argument: String? = null

    @ExperimentalLayoutApi
    @ExperimentalMaterialApi
    @ExperimentalMaterial3Api
    @ExperimentalFoundationApi
    override val ui: @Composable (modifier: Modifier) -> Unit = { modifier ->
        MainScreenHolder(modifier, navigateToPlayerScreen)
    }
}