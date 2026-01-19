package com.example.rpsfran.presentation.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpsfran.data.local.entity.Game
import com.example.rpsfran.data.local.entity.RoundEntity
import com.example.rpsfran.data.repos.GameRepository
import com.example.rpsfran.domain.model.GameChoice
import com.example.rpsfran.domain.model.GameResult
import com.example.rpsfran.domain.model.GameState
import com.example.rpsfran.domain.model.Round
import com.example.rpsfran.domain.usecase.DetermineWinnerUseCase
import com.example.rpsfran.domain.usecase.GenerateIAChoiceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    private val repository: GameRepository,
    playerName: String,
    totalRounds: Int
) : ViewModel() {

    private val determineWinnerUseCase = DetermineWinnerUseCase()
    private val generateIAChoiceUseCase = GenerateIAChoiceUseCase()

    private val _gameState = MutableStateFlow(
        GameState(playerName = playerName, totalRounds = totalRounds)
    )
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var currentGameId: Long = 0

    fun playRound(playerChoice: GameChoice) {
        viewModelScope.launch {
            val actualPlayerChoice = if (playerChoice == GameChoice.RANDOM) {
                listOf(GameChoice.PIEDRA, GameChoice.PAPEL, GameChoice.TIJERAS)[Random.nextInt(3)]
            } else {
                playerChoice
            }

            val iaChoice = generateIAChoiceUseCase()
            val result = determineWinnerUseCase(actualPlayerChoice, iaChoice)

            val newRound = Round(
                roundNumber = _gameState.value.currentRound + 1,
                playerChoice = actualPlayerChoice,
                iaChoice = iaChoice,
                result = result
            )

            val newPlayerScore = _gameState.value.playerScore + if (result == GameResult.WIN) 1 else 0
            val newIAScore = _gameState.value.iaScore + if (result == GameResult.LOSE) 1 else 0
            val newCurrentRound = _gameState.value.currentRound + 1
            val isFinished = newCurrentRound >= _gameState.value.totalRounds

            _gameState.value = _gameState.value.copy(
                currentRound = newCurrentRound,
                playerScore = newPlayerScore,
                iaScore = newIAScore,
                rounds = _gameState.value.rounds + newRound,
                isGameFinished = isFinished,
                lastRoundResult = result
            )

            if (isFinished) {
                saveGameToDatabase()
            }
        }
    }

    private suspend fun saveGameToDatabase() {
        val state = _gameState.value
        val winner = when {
            state.playerScore > state.iaScore -> state.playerName
            state.iaScore > state.playerScore -> "IA: ROBO-CRACK"
            else -> "Empate"
        }

        val game = Game(
            playerName = state.playerName,
            totalRounds = state.totalRounds,
            playerScore = state.playerScore,
            iaScore = state.iaScore,
            winner = winner
        )

        currentGameId = repository.insertGame(game)

        val roundEntities = state.rounds.map { round ->
            RoundEntity(
                gameId = currentGameId,
                roundNumber = round.roundNumber,
                playerChoice = round.playerChoice,
                iaChoice = round.iaChoice,
                result = round.result
            )
        }

        repository.insertRounds(roundEntities)
    }

    fun resetLastResult() {
        _gameState.value = _gameState.value.copy(lastRoundResult = null)
    }
}