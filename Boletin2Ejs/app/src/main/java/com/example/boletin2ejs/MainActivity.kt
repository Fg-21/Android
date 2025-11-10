package com.example.boletin2ejs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boletin2ejs.ui.theme.Boletin2EjsTheme
import com.example.boletin2ejs.ui.views.Ej1
import com.example.boletin2ejs.ui.views.ListaDestinos
import com.example.boletin2ejs.viewmodels.ListaDestinosVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "ListaBotone")
            {
                composable("ListaBotone") { ListaBotone(navController) }
                composable("ListaDestinos") {
                    val vm = ListaDestinosVM()
                    ListaDestinos(vm.getListaViajes()) }
            }
        }
    }
}


@Composable
fun ListaBotone(nav: NavController) {
    Column {
        Row (
            Modifier.padding(13.dp).fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){ BotonNavegar(nav, "Ej1", "ListaDestinos") }

    }
}

@Composable
fun BotonNavegar(nav: NavController, titulo: String, pantalla: String) {
    Button(onClick = {
        nav.navigate(pantalla)
    }) {Text(titulo) }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Boletin2EjsTheme {
    }
}