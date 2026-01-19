package com.example.rpsfran.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithRounds(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val rounds: List<RoundEntity>
)
