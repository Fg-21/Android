package com.example.ejerciciosjetpackcomposeboletin1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejerciciosjetpackcomposeboletin1.ui.theme.EjerciciosJetpackComposeBoletin1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosJetpackComposeBoletin1Theme {
                var texto by remember {mutableStateOf("Hola desconocido") }

                Column(modifier = Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(texto)
                    Button(onClick = {
                        texto = "Has pulsado el boton"
                    }) {Text("Pulsa")}
                    NavButton("ej2", "ej2Activity")
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun NavButton(texto: String, destino: String){
    val contexto = LocalContext.current
    val intent = Intent(contexto, destino::class.java)
    Button(onClick = {
        contexto.startActivity(intent)
    }) {Text(texto)}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjerciciosJetpackComposeBoletin1Theme {
        Greeting("Android")
    }
}