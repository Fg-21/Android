package com.example.ejemplojc

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplojc.ui.theme.EjemploJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EjemploJCTheme {




                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()) {
                    Img()
                    Casilla()
                    Casilla()
                    Btn()
                }



            }
        }
    }
}

@Composable
fun Img() {

        Image(
            painter = painterResource(id = R.drawable._128563401_gettyimages_1463993781),
            contentDescription = "GOAT",
            Modifier.size(150.dp)
        )


}
@Composable
fun Btn(){
    val context= LocalContext.current
    Button(
        onClick = {       Toast.makeText(context,"HOLA",Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        // Texto del botón
        Text(
            text = "Púlsame",
            fontSize = 18.sp, // Tamaño de fuente
            color = Color.White
        )
    }

}

@Composable
fun Casilla(){
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier.padding(10.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploJCTheme {
        Btn()
        Casilla()
    }
}