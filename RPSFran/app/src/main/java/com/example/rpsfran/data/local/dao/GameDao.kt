package com.example.rpsfran.data.local.dao

import androidx.room.*
import com.example.rpsfran.data.local.entity.Game
import com.example.rpsfran.data.local.entity.GameWithRounds
import com.example.rpsfran.data.local.entity.RoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRounds(rounds: List<RoundEntity>)

    @Transaction
    @Query("SELECT * FROM games ORDER BY timestamp DESC")
    fun getAllGamesWithRounds(): Flow<List<GameWithRounds>>

    @Transaction
    @Query("SELECT * FROM games WHERE playerName = :playerName ORDER BY timestamp DESC")
    fun getGamesByPlayer(playerName: String): Flow<List<GameWithRounds>>

    @Transaction
    @Query("SELECT * FROM games WHERE id = :gameId")
    suspend fun getGameWithRounds(gameId: Long): GameWithRounds?

    @Query("SELECT * FROM games ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentGames(limit: Int): Flow<List<Game>>

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGame(gameId: Long)

    @Query("SELECT COUNT(*) FROM games WHERE playerName = :playerName")
    suspend fun getGamesCountByPlayer(playerName: String): Int

    @Query("SELECT COUNT(*) FROM games WHERE playerName = :playerName AND winner = :playerName")
    suspend fun getWinsCountByPlayer(playerName: String): Int
}