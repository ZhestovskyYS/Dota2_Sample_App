package com.example.screens.main.impl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.screens.main.api.usecases.GetPlayersUseCase
import com.example.screens.main.impl.usecases.GetPlayerUseCaseImpl

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun MainScreenHolder(modifier: Modifier = Modifier) {
   MainScreen(
      modifier,
      viewModel = viewModel()
   )
}