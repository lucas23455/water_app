package com.example.water_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import com.example.water_app.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var bt_calcular: Button
    private lateinit var txt_resultado_ml: TextView
    private lateinit var ic_redefinir_dados: ImageView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        iniciarComponents()

        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        bt_calcular.setOnClickListener {
            if (edit_peso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            } else if (edit_idade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            } else {
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotalMl(peso, idade) // Chamada do método em minúsculas
                resultadoMl = calcularIngestaoDiaria.ResultadoMl() // Correção do método para obter o resultado

                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false
                txt_resultado_ml.text = formatar.format(resultadoMl) + "ml"
            }
        }

        ic_redefinir_dados.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("OK",{alertDialog, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_resultado_ml.text = ""

                })
            alertDialog.setNegativeButton("Cancelar",{alertDialog, i ->

            })
            val dialog = alertDialog.create()
            dialog.show()
        }
    }

    private fun iniciarComponents() {
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.bt_calcular)
        txt_resultado_ml = findViewById(R.id.txt_resultado_ml)
        ic_redefinir_dados = findViewById(R.id.ic_redefinir)
    }
}