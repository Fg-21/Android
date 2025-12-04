package com.example.contactossqlite.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contactossqlite.domain.entities.Contacto

@Dao
interface ContactoDao {
    @Query("SELECT * FROM contacto")
    suspend fun getAllContact(): List<Contacto>

    @Query("SELECT * FROM contacto WHERE id like :id")
    suspend fun getContactById(id: Int): Contacto

    @Insert
    suspend fun addContact(newContact: Contacto)

    @Update
    suspend fun updateContact(eContact: Contacto)

    @Delete
    suspend fun deleteContact(toDeleteContact: Contacto)
}