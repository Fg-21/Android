package com.example.rpsfran.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rpsfran.data.local.entity.GameWithRounds
import com.example.rpsfran.data.repos.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HistoryUiState(
    val games: List<GameWithRounds> = emptyList(),
    val isLoading: Boolean = true
)

class HistoryViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            repository.getAllGamesWithRounds().collect { games ->
                _uiState.value = _uiState.value.copy(
                    games = games,
                    isLoading = false
                )
            }
        }
    }

    fun deleteGame(gameId: Long) {
        viewModelScope.launch {
            repository.deleteGame(gameId)
        }
    }

    fun deleteAllGames() {
        viewModelScope.launch {
            repository.deleteAllGames()
        }
    }
}