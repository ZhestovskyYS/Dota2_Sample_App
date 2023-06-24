package com.example.screens.main.impl.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.Player
import com.example.screens.main.api.usecases.GetPlayersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetPlayerUseCaseImpl : GetPlayersUseCase {
    private val players = listOf(
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


    override suspend fun invoke(searchPattern: String): ProcessState<List<Player>> =
        withContext(Dispatchers.IO) {
            delay(500L)
            ProcessState.Success(
                players.filter { it.nickname.contains(searchPattern, ignoreCase = true) }
            )
    }

}