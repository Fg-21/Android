package com.example.listacontactos.data.repos

import com.example.listacontactos.domain.entities.Contacto

object Repo {
    val lista = mutableListOf(
        Contacto(1, "Juan García", 611123456, 'M'),
        Contacto(2, "María López", 678456123, 'F'),
        Contacto(3, "Raúl Cimas", 644789456, 'M'),
        Contacto(4, "Ana Morantes", 693882147, 'F'),
        Contacto(5, "Luis Fernández", 622334455, 'M'),
        Contacto(6, "Carmen Ruiz", 699112233, 'F'),
        Contacto(7, "Pablo Sánchez", 677445566, 'M'),
        Contacto(8, "Laura Gómez", 688556677, 'F'),
        Contacto(9, "Andrés Martín", 690778899, 'M'),
        Contacto(10, "Elena Navarro", 665998877, 'F'),
        Contacto(11, "Miguel Torres", 676223344, 'M'),
        Contacto(12, "Sara Domínguez", 633445566, 'F'),
        Contacto(13, "Javier Molina", 672889900, 'M'),
        Contacto(14, "Clara Vega", 684223355, 'F'),
        Contacto(15, "Diego Ramos", 699445577, 'M'),
        Contacto(16, "Isabel Romero", 691223344, 'F'),
        Contacto(17, "Hugo Castillo", 662334455, 'M'),
        Contacto(18, "Marta Herrera", 647889900, 'F'),
        Contacto(19, "Roberto Nieto", 670112233, 'M'),
        Contacto(20, "Nuria Pardo", 687334455, 'F'),
        Contacto(21, "Adrián Santos", 692998877, 'M'),
        Contacto(22, "Patricia León", 678223344, 'F'),
        Contacto(23, "Víctor Serrano", 666445577, 'M'),
        Contacto(24, "Beatriz Cano", 695889911, 'F')
    )
    fun getAllContacts(): List<Contacto> {
        return lista
    }
    fun nextID() : Int{
        return lista.last().id + 1;
    }

    fun añadir(contacto: Contacto){
        lista.add(contacto)
    }

}