package com.example.ejemplomvvm.models

object RepoUsers {
    val lista = mutableListOf<User>(
        User(1, "Juan Pérez"),
        User(2, "María Gómez"),
        User(3, "Carlos Ruiz"),
        User(4, "Lucía Fernández"),
        User(5, "Pedro López"),
        User(6, "Ana Torres"),
        User(7, "Jorge Ramírez"),
        User(8, "Sofía Morales"),
        User(9, "Andrés Navarro"),
        User(10, "Elena Castillo")
    )
    fun getUsers(): List<User> {
        return lista.toList()
    }
    fun insert(user:User) {
        lista.add(user)
    }
}

