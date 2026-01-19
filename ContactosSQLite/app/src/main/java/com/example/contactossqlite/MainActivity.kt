package com.example.contactossqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
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
import androidx.room.Room
import com.example.contactossqlite.data.db.ContactDatabase
import com.example.contactossqlite.ui.theme.ContactosSQLiteTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var database: ContactDatabase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactosSQLiteTheme {
                formulario()
            }
        }
    }
}




@Composable
fun formulario(){
    var textoName by remember { mutableStateOf("Nombre") }
    var textoSurname by remember { mutableStateOf("Apellido") }
    var textoTlf by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Row{
            Text(text = "Añadir Contacto")
        }
        Row {
            OutlinedTextField(value = textoName, onValueChange = {
                newText -> textoName = newText
            })
        }
        Row {
            OutlinedTextField(value = textoSurname, onValueChange = {
                    newText -> textoSurname = newText
            })
        }
        Row {
            OutlinedTextField(value = textoTlf, onValueChange = {
                    newText -> textoTlf = newText
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactosSQLiteTheme {
        formulario()
    }
}