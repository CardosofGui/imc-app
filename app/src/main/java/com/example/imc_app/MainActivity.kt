package com.example.imc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var btnMasculino : LinearLayout
    lateinit var btnFeminino : LinearLayout
    lateinit var seekAltura : SeekBar
    lateinit var seekPeso : SeekBar
    lateinit var btnCalcular : Button
    lateinit var txtAltura : TextView
    lateinit var txtPeso : TextView

    var homemSelecionado = false
    var mulherSelecionada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMasculino = findViewById(R.id.btnMasculino)
        btnFeminino = findViewById(R.id.btnFeminino)
        seekAltura = findViewById(R.id.seekAltura)
        seekPeso = findViewById(R.id.seekPeso)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtAltura = findViewById(R.id.txtAltura)
        txtPeso = findViewById(R.id.txtPeso)

        listeners()

        btnMasculino.setOnClickListener { sexoClicado(true, false) }
        btnFeminino.setOnClickListener { sexoClicado(false, true) }
        btnCalcular.setOnClickListener { calcularIMC() }
    }

    private fun sexoClicado(sexoMasculino : Boolean, sexoFeminino : Boolean){
        if(sexoMasculino) {
            btnMasculino.setBackgroundResource(R.drawable.background_sex_select_true)
            homemSelecionado = true
        } else {
            btnMasculino.setBackgroundResource(R.drawable.background_sex_select_false)
            homemSelecionado = false
        }

        if(sexoFeminino) {
            btnFeminino.setBackgroundResource(R.drawable.background_sex_select_true)
            mulherSelecionada = true
        } else {
            btnFeminino.setBackgroundResource(R.drawable.background_sex_select_false)
            mulherSelecionada = false
        }
    }

    private fun listeners(){
        seekAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtAltura.setText("$progress CM")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                txtAltura.setText("${seekAltura.progress} CM")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                txtAltura.setText("${seekAltura.progress} CM")
            }

        })


        seekPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtPeso.setText("${seekPeso.progress} KG")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                txtPeso.setText("${seekPeso.progress} KG")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                txtPeso.setText("${seekPeso.progress} KG")
            }

        })
    }

    private fun calcularIMC(){
        val peso = seekPeso.progress
        val altura : Double = (seekAltura.progress.toDouble() / 100)
        val intent : Intent = Intent(this, exibirResultado::class.java)

        if(peso > 0 && altura > 0.0){
            val calculoIMC : Double = (peso / (altura * altura))

            if(homemSelecionado){
                intent.putExtra("sexo", "Masculino")
                intent.putExtra("resultadoIMC", calculoIMC)
                startActivity(intent)
            }else if(mulherSelecionada){
                intent.putExtra("sexo", "Feminino")
                intent.putExtra("resultadoIMC", calculoIMC)
                startActivity(intent)
            }else{
                Toast.makeText(baseContext, "Selecione o seu Sexo", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(baseContext, "Digite valores maior que 0", Toast.LENGTH_SHORT).show()
        }
    }
}