package com.example.rpsfran.domain.model

data class GameState(
    val playerName: String = "",
    val totalRounds: Int = 3,
    val currentRound: Int = 0,
    val playerScore: Int = 0,
    val iaScore: Int = 0,
    val rounds: List<Round> = emptyList(),
    val isGameFinished: Boolean = false,
    val lastRoundResult: GameResult? = null
)
