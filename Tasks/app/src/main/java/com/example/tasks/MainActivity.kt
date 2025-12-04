package com.example.tasks

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.tasks.data.db.TaskDB
import com.example.tasks.domain.entities.TaskEntity
import com.example.tasks.ui.theme.TasksTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var database: TaskDB
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(applicationContext, TaskDB::class.java, "tareas-db").build()
        enableEdgeToEdge()
        setContent {
            TasksTheme {
                Column(Modifier.fillMaxSize().padding(15.dp)) {
                    barraAddTask()
                    listaTareas()
                }
            }
        }
    }
}

@Composable
fun listaTareas() {
    val coroutineScope = rememberCoroutineScope()
    val lista = remember { mutableStateListOf<TaskEntity>() }
    LaunchedEffect(Unit) {
        lista.clear()
        lista .addAll(
            MainActivity.database.taskDao().getAllTask()
        )
    }
    val context = LocalContext.current.applicationContext
    Column(Modifier.fillMaxWidth()) {
        Column {
            lista.forEach { item ->
                var checkedItem by remember { mutableStateOf(false) }
                Row (Modifier.fillMaxWidth()) {
                    Checkbox(checked = checkedItem, onCheckedChange = {
                        checkedStatus -> checkedItem = checkedStatus
                        item.isDone = !item.isDone
                        coroutineScope.launch {
                            MainActivity.database.taskDao().updateTask(item)
                        }
                    })
                    Text(item.name)
                }
            }
        }
    }
}

@Composable
fun barraAddTask(){
    val coroutineScope = rememberCoroutineScope()
    var newTask by remember { mutableStateOf("") }
    Row {
        TextField(value = newTask, onValueChange = {newTask = it})
        Button(onClick = {
            coroutineScope.launch { 
                MainActivity.database.taskDao().addTask(TaskEntity(name = newTask))
            }
        }) {
            Text("+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TasksTheme {
        listaTareas()
    }
}