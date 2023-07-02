package com.example.screens.main.impl.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.Player
import com.example.screens.main.api.usecases.GetPlayersUseCase
import com.exapmple.domain.feature.ReposProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetPlayerUseCaseImpl : GetPlayersUseCase {
    private val repository = ReposProvider.profileRepo

    override suspend fun invoke(searchPattern: String): ProcessState<List<Player>> =
        withContext(Dispatchers.IO) {
            try {
                val response = repository.searchPlayer(searchPattern)
                ProcessState.Success(
                    response.map {
                        Player(
                            id = it.id,
                            nickname = it.nickname,
                            avatar = it.avatarUrl
                        )
                    }
                )
            } catch (e: Exception) {
                ProcessState.Error(e)
            }
        }

}