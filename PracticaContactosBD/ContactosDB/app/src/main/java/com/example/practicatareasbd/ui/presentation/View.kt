package com.example.practicatareasbd.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
// Importación corregida
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- PALETA DE COLORES VIBRANTES ---
val ColorPrimaryVibrant = Color(0xFF00ADB5) // Turquesa
val ColorSecondaryAccent = Color(0xFFFF2E63) // Rosa/Fucsia
val ColorBackgroundLight = Color(0xFFEEEEEE) // Fondo claro
val ColorIconBackground = Color(0xFF00ADB5) // Turquesa
val ColorGradientStart = Color(0xFF00ADB5) // Turquesa
val ColorGradientEnd = Color(0xFF00C29F) // Verde Mar

// --- HELPER PARA IMÁGENES ---
val availableImages = mapOf(
    "face" to Icons.Default.Face,
    "person" to Icons.Default.Person,
    "star" to Icons.Default.Star,
    "account" to Icons.Default.AccountCircle,
    "call" to Icons.Default.Call
)

fun getIconByName(name: String): ImageVector {
    return availableImages[name] ?: Icons.Default.Person
}

// Suponiendo que tienes esta clase de datos en tu proyecto
data class Contact(val id: Int = 0, val nombre: String, val apellidos: String, val telefono: String, val imagenReferencia: String)

// --- PANTALLA 1: LISTA DE CONTACTOS (ESTILO VIBRANTE) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavController, viewModel: ContactosViewModel) {
    val contactos by viewModel.listaContactos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Contactos", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorPrimaryVibrant,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("form") },
                containerColor = ColorSecondaryAccent,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir Contacto", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = ColorBackgroundLight
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(contactos) { contacto ->
                    ContactItem(contact = contacto)
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: com.example.practicatareasbd.data.Contact) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono con degradado vibrante
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(ColorGradientStart, ColorGradientEnd))),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getIconByName(contact.imagenReferencia),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${contact.nombre} ${contact.apellidos}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = contact.telefono,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Detalle",
                tint = ColorPrimaryVibrant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// --- PANTALLA 2: FORMULARIO (ESTILO VIBRANTE) ---
@Composable
fun FormularioScreen(navController: NavController, viewModel: ContactosViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var selectedImageKey by remember { mutableStateOf("face") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Nuevo Contacto",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = ColorPrimaryVibrant
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Selecciona un ícono:",
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Selector de imagen vibrante
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(availableImages.toList()) { (key, icon) ->
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (selectedImageKey == key) 4.dp else 2.dp,
                            color = if (selectedImageKey == key) ColorSecondaryAccent else Color.LightGray,
                            shape = CircleShape
                        )
                        .background(Color.White)
                        .clickable { selectedImageKey = key },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = if (selectedImageKey == key) ColorSecondaryAccent else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Campos de texto con colores de acento
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = ColorPrimaryVibrant) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ColorPrimaryVibrant, unfocusedLabelColor = Color.Gray, focusedLabelColor = ColorPrimaryVibrant),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = ColorPrimaryVibrant) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ColorPrimaryVibrant, unfocusedLabelColor = Color.Gray, focusedLabelColor = ColorPrimaryVibrant),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            leadingIcon = { Icon(Icons.Default.Call, contentDescription = null, tint = ColorPrimaryVibrant) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ColorPrimaryVibrant, unfocusedLabelColor = Color.Gray, focusedLabelColor = ColorPrimaryVibrant),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botón de Guardar
        Button(
            onClick = {
                if (nombre.isNotBlank() && telefono.isNotBlank()) {
                    viewModel.agregarContacto(nombre, apellidos, telefono, selectedImageKey)
                    navController.popBackStack()
                }
            },
            enabled = nombre.isNotBlank() && telefono.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = ColorSecondaryAccent),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Guardar Contacto", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón de Cancelar
        TextButton(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.textButtonColors(contentColor = ColorPrimaryVibrant)
        ) {
            Text("Cancelar", fontWeight = FontWeight.SemiBold)
        }
    }
}