package com.example.tasks.domain.entities

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "task_entity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var isDone: Boolean = false
)
