package com.example.rpsfran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val playerName: String,
    val totalRounds: Int,
    val playerScore: Int,
    val iaScore: Int,
    val winner: String,
    val timestamp: Long = System.currentTimeMillis()
)
