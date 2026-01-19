package com.example.rpsfran.presentation.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rpsfran.data.repos.GameRepository

class GameViewModelFactory(
    private val repository: GameRepository,
    private val playerName: String,
    private val totalRounds: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(repository, playerName, totalRounds) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}