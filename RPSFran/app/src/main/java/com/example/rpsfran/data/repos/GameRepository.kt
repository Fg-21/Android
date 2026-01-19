package com.example.rpsfran.data.repos

import com.example.rpsfran.data.local.dao.GameDao
import com.example.rpsfran.data.local.entity.Game
import com.example.rpsfran.data.local.entity.GameWithRounds
import com.example.rpsfran.data.local.entity.RoundEntity
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {

    fun getAllGamesWithRounds(): Flow<List<GameWithRounds>> =
        gameDao.getAllGamesWithRounds()

    fun getGamesByPlayer(playerName: String): Flow<List<GameWithRounds>> =
        gameDao.getGamesByPlayer(playerName)

    fun getRecentGames(limit: Int): Flow<List<Game>> =
        gameDao.getRecentGames(limit)

    suspend fun insertGame(game: Game): Long =
        gameDao.insertGame(game)

    suspend fun insertRounds(rounds: List<RoundEntity>) =
        gameDao.insertRounds(rounds)

    suspend fun getGameWithRounds(gameId: Long): GameWithRounds? =
        gameDao.getGameWithRounds(gameId)

    suspend fun deleteAllGames() =
        gameDao.deleteAllGames()

    suspend fun deleteGame(gameId: Long) =
        gameDao.deleteGame(gameId)

    suspend fun getGamesCountByPlayer(playerName: String): Int =
        gameDao.getGamesCountByPlayer(playerName)

    suspend fun getWinsCountByPlayer(playerName: String): Int =
        gameDao.getWinsCountByPlayer(playerName)
}