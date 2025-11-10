package com.example.tarea13

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tarea13.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate() creado")

        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val botonEntrar = binding.entrarbtn
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usuario o contraseña incorrectos")
        val alerta = builder.create()

        setContentView(binding.root)

        botonEntrar.setOnClickListener {
            val textoUser = binding.usuarioText.text.toString()
            val textoContra = binding.contraseAText.text.toString()
            if (textoUser == "admin" && textoContra == "admin"){
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.putExtra("nombre", textoUser)
                startActivity(intent)
            } else {
                alerta.show()
            }
        }
    }
    override fun onStart(){
    super.onStart()
        Log.d("TAG", "onStart() llamado")
    }

    override fun onResume(){
        super.onResume()
        Log.d("TAG", "onResume() llamado - ¡La Activity es visible y activa!")
    }

    override fun onPause(){
        super.onPause()
        Log.d("TAG", "onPause() llamado - Otra Activity toma el foco")
    }

    override fun onStop(){
        super.onStop()
        Log.d("TAG", "onStop() llamado - La Activity ya no es visible")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("TAG", "onDestroy() llamado - La Activity está siendo destruida")
    }
    override fun onRestart(){
        super.onRestart()
        Log.d("TAG", "onRestart() llamado - Volviendo de estar 'stopped'")
    }
}

