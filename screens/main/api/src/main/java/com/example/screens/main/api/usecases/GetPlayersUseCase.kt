package com.example.screens.main.api.usecases

import com.example.screens.main.api.data.Player
import kotlinx.coroutines.flow.Flow

interface GetPlayersUseCase {
    suspend operator fun invoke(searchPattern: String): Flow<List<Player>>
}