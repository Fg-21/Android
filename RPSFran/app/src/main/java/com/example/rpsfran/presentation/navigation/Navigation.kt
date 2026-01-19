package com.example.rpsfran.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rpsfran.data.local.AppDatabase
import com.example.rpsfran.data.repos.GameRepository
import com.example.rpsfran.presentation.screen.game.GameScreen
import com.example.rpsfran.presentation.screen.game.GameViewModel
import com.example.rpsfran.presentation.screen.game.GameViewModelFactory
import com.example.rpsfran.presentation.screen.history.HistoryScreen
import com.example.rpsfran.presentation.screen.history.HistoryViewModel
import com.example.rpsfran.presentation.screen.history.HistoryViewModelFactory
import com.example.rpsfran.presentation.screen.welcome.WelcomeScreen
import com.example.rpsfran.presentation.screen.winner.WinnerScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Game : Screen("game/{playerName}/{rounds}") {
        fun createRoute(playerName: String, rounds: Int) = "game/$playerName/$rounds"
    }
    object Winner : Screen("winner/{winner}/{playerName}") {
        fun createRoute(winner: String, playerName: String) = "winner/$winner/$playerName"
    }
    object History : Screen("history")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val repository = GameRepository(database.gameDao())

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartGame = { playerName, rounds ->
                    navController.navigate(Screen.Game.createRoute(playerName, rounds))
                },
                onViewHistory = {
                    navController.navigate(Screen.History.route)
                }
            )
        }

        composable(Screen.Game.route) { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("playerName") ?: ""
            val rounds = backStackEntry.arguments?.getString("rounds")?.toIntOrNull() ?: 3

            val viewModel: GameViewModel = viewModel(
                factory = GameViewModelFactory(repository, playerName, rounds)
            )

            GameScreen(
                playerName = playerName,
                totalRounds = rounds,
                onGameFinished = { winner ->
                    navController.navigate(Screen.Winner.createRoute(winner, playerName)) {
                        popUpTo(Screen.Welcome.route)
                    }
                },
                viewModel = viewModel
            )
        }

        composable(Screen.Winner.route) { backStackEntry ->
            val winner = backStackEntry.arguments?.getString("winner") ?: ""
            val playerName = backStackEntry.arguments?.getString("playerName") ?: ""

            WinnerScreen(
                winner = winner,
                playerName = playerName,
                onPlayAgain = {
                    navController.navigate(Screen.Game.createRoute(playerName, 3)) {
                        popUpTo(Screen.Welcome.route)
                    }
                },
                onExit = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.History.route) {
            val viewModel: HistoryViewModel = viewModel(
                factory = HistoryViewModelFactory(repository)
            )

            HistoryScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}