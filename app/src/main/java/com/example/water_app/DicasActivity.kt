package com.example.water_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DicasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicas)

        // Botão para voltar à MainActivity
        val buttonVoltar = findViewById<Button>(R.id.button_voltar_dicas)
        buttonVoltar.setOnClickListener {
            finish() // Fecha a atividade atual e volta à anterior
        }
    }
}
