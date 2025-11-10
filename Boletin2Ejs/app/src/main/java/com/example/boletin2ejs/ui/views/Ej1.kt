package com.example.boletin2ejs.ui.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.boletin2ejs.R
import com.example.boletin2ejs.model.Viaje
import com.example.boletin2ejs.viewmodels.ListaDestinosVM

class Ej1 : AppCompatActivity() {

}

@Composable
fun ListaDestinos(lista: List<Viaje>) {
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(lista) { itemContact ->
            Card(Modifier.padding(13.dp)) {
                Text("${itemContact.destino} - ${itemContact.pais}")
            }
        }
    }
}