package com.example.ejerciciosjetpackcomposeboletin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejerciciosjetpackcomposeboletin1.ui.theme.EjerciciosJetpackComposeBoletin1Theme
import kotlin.random.Random

class ej6Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var numero by remember { mutableStateOf(5) }
            var aviso by remember { mutableStateOf("") }
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(numero.toString())
                Button(onClick = {
                    if (numero + 1 <= 10) {
                        numero++
                    }
                    if(numero == 10){
                        aviso = "Has alcanzado el maximo"
                    }
                })
                {Text("+")}
                Button(onClick = {
                    if (numero - 1 >= 0) {
                        numero--
                    }
                })
                {Text("-")}
                Button(onClick = {
                    numero = 0
                    aviso = ""
                })
                {Text("Reiniciar")}
                Text(aviso)
            }
        }
    }
}
