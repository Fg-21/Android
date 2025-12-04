package com.example.contactossqlite.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacto")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val numero: Long,
    val genero: String
)
