package com.example.rpsfran.presentation.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rpsfran.data.local.entity.GameWithRounds
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E88E5), Color(0xFF1565C0))
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Historial de Partidas",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    if (uiState.games.isNotEmpty()) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Default.Delete, contentDescription = "Borrar todo")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else if (uiState.games.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "📊",
                            fontSize = 80.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No hay partidas guardadas",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "¡Juega una partida para verla aquí!",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.games) { gameWithRounds ->
                        GameHistoryCard(
                            gameWithRounds = gameWithRounds,
                            onDeleteClick = { viewModel.deleteGame(gameWithRounds.game.id) }
                        )
                    }
                }
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Eliminar todo el historial") },
                text = { Text("¿Estás seguro de que quieres eliminar todas las partidas guardadas?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteAllGames()
                            showDeleteDialog = false
                        }
                    ) {
                        Text("Eliminar", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun GameHistoryCard(
    gameWithRounds: GameWithRounds,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val game = gameWithRounds.game
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = game.playerName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E88E5)
                    )
                    Text(
                        text = dateFormat.format(Date(game.timestamp)),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = onDeleteClick) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ScoreColumn("Jugador", game.playerScore, Color(0xFF4CAF50))
                ScoreColumn("IA", game.iaScore, Color(0xFFF44336))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when (game.winner) {
                        game.playerName -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                        "Empate" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                        else -> Color(0xFFF44336).copy(alpha = 0.1f)
                    }
                )
            ) {
                Text(
                    text = "🏆 Ganador: ${game.winner}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (game.winner) {
                        game.playerName -> Color(0xFF4CAF50)
                        "Empate" -> Color(0xFFFF9800)
                        else -> Color(0xFFF44336)
                    },
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }

            if (gameWithRounds.rounds.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = if (expanded) "Ocultar detalles ▲" else "Ver detalles ▼",
                        color = Color(0xFF1E88E5)
                    )
                }

                if (expanded) {
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    gameWithRounds.rounds.forEach { round ->
                        RoundDetailRow(round)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreColumn(label: String, score: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = score.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
fun RoundDetailRow(round: com.example.rpsfran.data.local.entity.RoundEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ronda ${round.roundNumber}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF424242)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = round.playerChoice.getEmoji(), fontSize = 20.sp)
            Text(text = "vs", fontSize = 12.sp, color = Color.Gray)
            Text(text = round.iaChoice.getEmoji(), fontSize = 20.sp)
            Text(
                text = when (round.result) {
                    com.example.rpsfran.domain.model.GameResult.WIN -> "✓"
                    com.example.rpsfran.domain.model.GameResult.LOSE -> "✗"
                    com.example.rpsfran.domain.model.GameResult.DRAW -> "="
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = when (round.result) {
                    com.example.rpsfran.domain.model.GameResult.WIN -> Color(0xFF4CAF50)
                    com.example.rpsfran.domain.model.GameResult.LOSE -> Color(0xFFF44336)
                    com.example.rpsfran.domain.model.GameResult.DRAW -> Color(0xFFFF9800)
                }
            )
        }
    }
}