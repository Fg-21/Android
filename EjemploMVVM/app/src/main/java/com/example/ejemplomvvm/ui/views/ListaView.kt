package com.example.ejemplomvvm.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ejemplomvvm.models.User
import com.example.ejemplomvvm.viewmodels.UserVM

class ListaView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @Composable
    fun UserListScreen(
        userViewModel: UserVM = viewModel()
    ) {
        val userList by userViewModel.users
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val newUser = User((userList.size + 1), "Nuevo Usuario ${userList.size + 1}")
                    userViewModel.insertUser(newUser)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Añadir Usuario")
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.Companion.padding(paddingValues).fillMaxSize()
            ) {
                items(userList) { user ->
                    UserRow(user = user)
                }
            }
        }
    }

    @Composable
    fun UserRow(user: User) {
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                text = user.id.toString(),
                fontWeight = FontWeight.Companion.Bold,
                modifier = Modifier.Companion.width(30.dp)
            )
            Spacer(modifier = Modifier.Companion.width(8.dp))
            Text(text = user.name)
        }
        Divider()

    }

}