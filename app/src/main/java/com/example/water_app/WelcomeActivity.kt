package com.example.water_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        // Esconde a ActionBar (opcional)
        supportActionBar?.hide()

        // Inicializa o botão
        val buttonStart = findViewById<Button>(R.id.button_start)

        // Define a ação ao clicar no botão
        buttonStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Fecha a WelcomeActivity para não voltar ao clicar no botão de voltar
        }
    }
}
