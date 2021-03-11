package com.example.imc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.round

class exibirResultado : AppCompatActivity() {

    lateinit var btnVoltar : Button
    lateinit var txtResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir_resultado)

        txtResultado = findViewById(R.id.txtResultadoIMC)
        btnVoltar = findViewById(R.id.btnVoltar)

        exibirResultado()
        btnVoltar.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun exibirResultado(){
        val sexo = intent.getStringExtra("sexo")
        val resultadoIMC = (intent.getDoubleExtra("resultadoIMC", 0.0)).round(2)

        if(sexo.equals("Masculino")){

            if(resultadoIMC < 20.7){
                txtResultado.setText("$resultadoIMC \nAbaixo do Peso")
                txtResultado.setTextColor(getColor(R.color.imc_razoavel))
            }else if(resultadoIMC < 26.4){
                txtResultado.setText("$resultadoIMC \nPeso Normal")
                txtResultado.setTextColor(getColor(R.color.imc_bom))
            }else if(resultadoIMC < 27.8){
                txtResultado.setText("$resultadoIMC \nAcima do Peso")
                txtResultado.setTextColor(getColor(R.color.imc_razoavel))
            }else if(resultadoIMC < 31.1){
                txtResultado.setText("$resultadoIMC \nObesidade I")
                txtResultado.setTextColor(getColor(R.color.imc_ruim))
            }else{
                txtResultado.setText("$resultadoIMC \nObesidade II")
                txtResultado.setTextColor(getColor(R.color.imc_ruim))
            }

        }else if(sexo.equals("Feminino")){

            if(resultadoIMC < 19.1){
                txtResultado.setText("$resultadoIMC \nAbaixo do Peso")
                txtResultado.setTextColor(getColor(R.color.imc_razoavel))
            }else if(resultadoIMC < 25.8){
                txtResultado.setText("$resultadoIMC \nPeso Normal")
                txtResultado.setTextColor(getColor(R.color.imc_bom))
            }else if(resultadoIMC < 27.3){
                txtResultado.setText("$resultadoIMC \nAcima do Peso")
                txtResultado.setTextColor(getColor(R.color.imc_razoavel))
            }else if(resultadoIMC < 32.3){
                txtResultado.setText("$resultadoIMC \nObesidade I")
                txtResultado.setTextColor(getColor(R.color.imc_ruim))
            }else{
                txtResultado.setText("$resultadoIMC \nObesidade II")
                txtResultado.setTextColor(getColor(R.color.imc_ruim))
            }

        }
    }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}