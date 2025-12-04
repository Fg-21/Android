package com.example.tasks.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasks.data.dao.TaskDao
import com.example.tasks.domain.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDB: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}