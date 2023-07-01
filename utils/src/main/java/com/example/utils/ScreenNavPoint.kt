package com.example.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ScreenNavPoint {
    val link: String
    var argument: String?

    val ui: @Composable (modifier: Modifier) -> Unit
}