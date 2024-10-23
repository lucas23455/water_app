package com.example.water_app

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editPeso: EditText
    private lateinit var editIdade: EditText
    private lateinit var btCalcular: Button
    private lateinit var txtResultadoMl: TextView
    private lateinit var icRedefinirDados: ImageView
    private lateinit var btLembrete: Button
    private lateinit var btAlarme: Button
    private lateinit var btVoltar: Button
    private lateinit var btDicas: Button
    private lateinit var txtHora: TextView
    private lateinit var txtMinutos: TextView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0

    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var calendario: Calendar
    private var horaAtual = 0
    private var minutosAtuais = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        iniciarComponents()

        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        btCalcular.setOnClickListener {
            val pesoText = editPeso.text.toString()
            val idadeText = editIdade.text.toString()

            if (pesoText.isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (idadeText.isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso = pesoText.toDouble()
            val idade = idadeText.toInt()
            calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
            resultadoMl = calcularIngestaoDiaria.ResultadoMl()

            val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
            formatar.isGroupingUsed = false
            txtResultadoMl.text = formatar.format(resultadoMl) + "ml"
        }

        btVoltar.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btDicas.setOnClickListener {
            val intent = Intent(this, DicasActivity::class.java)
            startActivity(intent)
        }

        icRedefinirDados.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("OK") { _, _ ->
                    editPeso.setText("")
                    editIdade.setText("")
                    txtResultadoMl.text = ""
                }
                .setNegativeButton("Cancelar", null)

            alertDialog.create().show()
        }

        btLembrete.setOnClickListener {
            calendario = Calendar.getInstance()
            horaAtual = calendario.get(Calendar.HOUR_OF_DAY)
            minutosAtuais = calendario.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minutes ->
                txtHora.text = String.format("%02d", hourOfDay)
                txtMinutos.text = String.format("%02d", minutes)
            }, horaAtual, minutosAtuais, true)
            timePickerDialog.show()
        }

        btAlarme.setOnClickListener {
            if (txtHora.text.isNotEmpty() && txtMinutos.text.isNotEmpty()) {
                val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                    putExtra(AlarmClock.EXTRA_HOUR, txtHora.text.toString().toInt())
                    putExtra(AlarmClock.EXTRA_MINUTES, txtMinutos.text.toString().toInt())
                    putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.alarme_mensagem))
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Defina a hora e os minutos antes de definir o alarme.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniciarComponents() {
        editPeso = findViewById(R.id.edit_peso)
        editIdade = findViewById(R.id.edit_idade)
        btCalcular = findViewById(R.id.bt_calcular)
        txtResultadoMl = findViewById(R.id.txt_resultado_ml)
        icRedefinirDados = findViewById(R.id.ic_redefinir)
        btLembrete = findViewById(R.id.bt_definir_lembrete)
        btAlarme = findViewById(R.id.bt_alarme)
        btVoltar = findViewById(R.id.bt_voltar)
        txtHora = findViewById(R.id.txt_hora)
        txtMinutos = findViewById(R.id.txt_minutos)
        btDicas = findViewById(R.id.bt_dicas)
    }
}
