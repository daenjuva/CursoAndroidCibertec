package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityImprimirBinding

class Imprimir : AppCompatActivity() {

    private lateinit var binding: ActivityImprimirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImprimirBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre") ?: "No hay data"
        val dni = intent.getStringExtra("dni") ?: "No hay data"
        val plan = intent.getStringExtra("plan") ?: "No hay EP2"
        val servicio = intent.getStringExtra("servicio") ?: "No hay EP2"
        val instalacion = intent.getStringExtra("instalacion") ?: "No hay EP2"
        val descuento = intent.getStringExtra("descuento") ?: "No hay EP2"
        val total = intent.getStringExtra("total") ?: "No hay EP2"

        binding.txtImprimir.text = "RESUMEN DE SERVICIO\n\n"  + "NOMBRE CLIENTE: $nombre \n " +
                "DNI/TP: $dni \n " +
                "SERVICIO: $plan \n " +
                "COSTO SERVICIO: $servicio \n " +
                "COSTO INSTALACION: $instalacion \n " +
                "DESCUENTO: $descuento \n " +
                "TOTAL A PAGAR: $total \n"

    }
}