package com.example.ej1tipoexamen

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ej1tipoexamen.ui.theme.EJ1TipoExamenTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navC = rememberNavController()

            EJ1TipoExamenTheme {
                NavHost(
                    navController = navC,
                    startDestination = "eleccion/10"
                ) {
                    composable("eleccion/{creds}") { backStackEntry ->
                        val creds = backStackEntry.arguments?.getString("creds")
                        eleccion(navC = navC, creds = creds.toString())
                    }
                    composable("apuesta/{numElegido}/{creds}") { backStackEntry ->
                        val numeroElegido = backStackEntry.arguments?.getString("numElegido")
                        val creds = backStackEntry.arguments?.getString("creds")
                        apuesta(
                            navC = navC,
                            numeroElegido = numeroElegido.toString(),
                            creds = creds.toString()
                        )
                    }
                    composable("resultado/{num}/{apuesta}/{creds}") { backStackEntry ->
                        val numeroElegido = backStackEntry.arguments?.getString("num")
                        val apuesta = backStackEntry.arguments?.getString("apuesta")
                        var creds = backStackEntry.arguments?.getString("creds")
                        resultado(
                            navC = navC,
                            num = numeroElegido.toString(),
                            apuesta = apuesta.toString(),
                            creds = creds.toString()
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun eleccion(navC: NavController, creds: String) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..2) {

            Row {
                for (j in 1..3) {
                    Column {
                        Button(
                            onClick = { navC.navigate("apuesta/${j + (3 * i)}/${creds}") },
                            Modifier.padding(10.dp)
                        ) {
                            Text(
                                "${j + (3 * i)}"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun apuesta(navC: NavController, numeroElegido: String, creds: String) {
    var creditos = creds.toInt()
    var apuesta by remember { mutableStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Column(Modifier.padding(10.dp)) { Text("Saldo: ${creditos}") }
            Column(Modifier.padding(10.dp)) { Text("Número Elegido: ${numeroElegido}") }
        }
        Row {
            Text("¿Cuánto apuestas?")
        }
        Row {
            Column {
                Button(onClick = {
                    if (apuesta < creditos) {
                        apuesta++
                    }
                }) { Text("+") }
            }
            Column(Modifier.padding(10.dp)) {
                Text("${apuesta}")
            }
            Column {
                Button(onClick = {
                    if (apuesta > 0) {
                        apuesta--
                    }
                }) { Text("-") }
            }
        }
        Row {
            Button(onClick = {
                navC.navigate("resultado/${numeroElegido}/${apuesta}/${creditos}")
            }) { Text("Apostar") }
        }
    }

}

@Composable
fun resultado(navC: NavController, num: String, apuesta: String, creds: String) {
    val sorteo = Random.nextInt(1, 9)
    var victoria = false
    var resultado = 0
    if (sorteo == num.toInt()) {
        victoria = true
        resultado = creds.toInt() + apuesta.toInt() * 2
    } else {
        resultado = creds.toInt() - apuesta.toInt()
    }



    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Resultado: ${sorteo}", fontSize = 30.sp)
        val texto =
            if (victoria) "Has ganado! Tus creditos ahora serán: ${resultado}" else "Has perdido. Saldo Restante: ${resultado}"
        Text(texto)

        Row {
            Button(onClick = {
                navC.navigate("eleccion/${resultado}")
            }) {
                Text("Jugar de Nuevo")
            }
            Button(onClick = {
                resultado = 10
                navC.navigate("eleccion/${resultado}")
            }) {
                Text("Salir")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EJ1TipoExamenTheme {

    }
}