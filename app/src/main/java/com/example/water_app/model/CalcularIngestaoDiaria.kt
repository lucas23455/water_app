package com.example.water_app.model

class CalcularIngestaoDiaria {

    private val ML_JOVEM = 40.0
    private val ML_ADULTO = 35.0
    private val ML_IDOSO = 30.0
    private val ML_MAIS_DE_66_ANOS = 25.0

    private var resultadoML = 0.0
    private var resultado_Total_ml = 0.0

    fun CalcularTotalMl(peso: Double, idade: Int) {
        if (idade <= 17) {
            resultadoML = peso * ML_JOVEM
            resultado_Total_ml = resultadoML
        } else if (idade <= 55) {
            resultadoML = peso * ML_ADULTO
            resultado_Total_ml = resultadoML
        } else if (idade <= 65) {
            resultadoML = peso * ML_IDOSO
            resultado_Total_ml = resultadoML
        } else {
            resultadoML = peso * ML_MAIS_DE_66_ANOS
            resultado_Total_ml = resultadoML
        }
    }

    fun ResultadoMl(): Double {
        return resultado_Total_ml
    }
}
