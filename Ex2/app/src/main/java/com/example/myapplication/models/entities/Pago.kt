package com.example.myapplication.models.entities

data class Pago(
    val id: Int,
    var cantidad: Number,
    var numPersonas: Int,
    var personasEnPago: List<Persona>
)


