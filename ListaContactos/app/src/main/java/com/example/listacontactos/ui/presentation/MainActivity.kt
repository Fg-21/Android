package com.example.listacontactos.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listacontactos.R
import com.example.listacontactos.data.repos.Repo
import com.example.listacontactos.domain.entities.Contacto
import kotlinx.serialization.descriptors.PrimitiveKind

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _root_ide_package_.com.example.listacontactos.ui.theme.ListaContactosTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "contactos"
                ) {
                    composable("contactos") { ContactsScreen(navController) }
                    composable("formulario") { FormularioAñadirContacto(navController) }
                }



            }
        }
    }
}




@Composable
fun ContactRow(contacto: Contacto) {
var show by remember { mutableStateOf(false) };
        Card(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Row {
                if (contacto.genre.equals('M')){
                    Image(
                        painter = painterResource(id = R.drawable.mas),
                        contentDescription = "Foto contacto",
                        Modifier.height(100.dp).clickable{
                            show = !show;
                        })
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.fem),
                        contentDescription = "Foto contacto",
                        Modifier.height(100.dp).clickable{
                            show = !show;
                        })
                }


                Spacer(modifier = Modifier.padding(58.dp))
                Column {
                    if (show){
                        ContactRowAll(contacto = contacto)
                    } else {
                        ContactRowIniciales(contacto = contacto)
                    }
                }
            }  }
    }

@Composable
fun ContactRowAll(contacto: Contacto) {
                Text(
                    text = contacto.name,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.tlf.toString(),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
@Composable
fun ContactRowIniciales(contacto: Contacto) {
    Text(
        text = contacto.name.filter { it.isUpperCase() },
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
}



@Composable
fun ContactsScreen(nav: NavController, modifier: Modifier = Modifier) {
    val lista = Repo.getAllContacts()
    var show = false;
    Scaffold(modifier = Modifier.fillMaxSize().padding(20.dp)) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {	// Scroll vertical,
            items(lista) { itemContacto ->
                    ContactRow(contacto = itemContacto)
                }
            }
        FloatingActionButton(onClick = {
            nav.navigate("formulario")
        }) {Text("+")}
        }
    }

@Composable
fun FormularioAñadirContacto(nav: NavController, modifier: Modifier = Modifier){
    var textoNombre by remember { mutableStateOf("") }
    var textoTelefono by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf('?') }
    var chequeao by remember { mutableStateOf(false) }

    val opcion = listOf<String>("Masculino", "Femenino")
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = textoNombre,
                onValueChange = {newText -> textoNombre = newText},
                label = {Text("Nombre")}
            )
        }
        Row {
            TextField(
                value = textoTelefono,
                onValueChange = {newText -> textoTelefono = newText},
                label = {Text("Telefono")}
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Género", style = MaterialTheme.typography.bodyLarge)
            opcion.forEach { opcion ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    RadioButton(
                        selected = (genero == if (opcion == "Masculino") 'M' else 'F'),
                        onClick = {
                            genero = if (opcion == "Masculino") 'M' else 'F'
                        }
                    )
                    Text(opcion)
                }
            }
        }
        }

        Row {
            Button(onClick = {
                Repo.añadir(Contacto(Repo.nextID(), textoNombre, textoTelefono.toInt(), genero))
                nav.navigate("contactos")
            }) {Text("Guardar") }
        }

    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _root_ide_package_.com.example.listacontactos.ui.theme.ListaContactosTheme {

    }
}