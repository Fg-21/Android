package com.example.boletin2ejs.viewmodels

import com.example.boletin2ejs.model.RepoViajes
import com.example.boletin2ejs.model.Viaje

class ListaDestinosVM {
    fun getListaViajes(): List<Viaje>{
        return RepoViajes.listaViajes
    }
}