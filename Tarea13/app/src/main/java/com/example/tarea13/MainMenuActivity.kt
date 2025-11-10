package com.example.tarea13

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.tarea13.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate() creado")
        enableEdgeToEdge()

        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        Log.d("ciclo", "Estoy en el onCreate en Main")
        setContentView(binding.root)

        val name = intent.getStringExtra("nombre")
        val welcome= binding.welcomeText.text.toString()
        binding.welcomeText.text = welcome + name + "!"

        binding.browser.setOnClickListener {
            nav(binding.utilityText.text.toString())
        }

        binding.message.setOnClickListener {
            message(binding.utilityText.text.toString())
        }

        binding.share.setOnClickListener {
            share(binding.utilityText.text.toString())
        }


        binding.tlf.setOnClickListener {
            call(binding.utilityText.text.toString())
        }







    }



    //Navegador
    private fun nav(busqueda: String){
        val navIntent= Intent(Intent.ACTION_VIEW)
        navIntent.data= "https://www.google.com/search?q=${busqueda}".toUri()
        startActivity(navIntent)
        finish()

    }

    //Mensaje
    private fun message(number: String){
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.data = Uri.parse("smsto:" + number) // número escrito en el EditText
        startActivity(smsIntent)
        finish()

    }
    //Compartir
    private fun share(message: String){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message) // texto escrito en el EditText
        startActivity(Intent.createChooser(shareIntent, "Compartir con..."))
        finish()
    }
    //Llamar
    private fun call(number: String){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = "tel: ${number}".toUri()
        startActivity(callIntent)
        finish()
    }

    override fun onStart(){
        super.onStart()
        Log.d("TAG", "onStart() llamado")
    }

    override fun onResume(){
        super.onResume()
        Log.d("TAG", "onResume() llamado - ¡La Activity es visible y activa!")
        Toast.makeText(this, getString(R.string.toast_ab), Toast.LENGTH_SHORT).show()
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