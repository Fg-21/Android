package com.example.rpsfran.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.rpsfran.domain.model.GameChoice
import com.example.rpsfran.domain.model.GameResult

@Entity(
    tableName = "rounds",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gameId: Long,
    val roundNumber: Int,
    val playerChoice: GameChoice,
    val iaChoice: GameChoice,
    val result: GameResult
)
