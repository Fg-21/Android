package com.example.contactossqlite.data.db

import androidx.room.Database
import com.example.contactossqlite.data.dao.ContactoDao
import com.example.contactossqlite.domain.entities.Contacto

@Database(entities = [Contacto::class], version = 1)
abstract class ContactDatabase {
    abstract fun ContactoDao(): ContactoDao
}