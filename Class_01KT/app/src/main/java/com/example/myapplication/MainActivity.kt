package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("EjercicioKT", "onCreate")

        /* Evento click KT */
        binding.btnMove.setOnClickListener {
            val intent = Intent(this, Destino::class.java)
            startActivity(intent)
        }

        binding.txtHola.text = "Soy nuevo en KT"
    }

    override fun onResume() {
        super.onResume()
        Log.d("EjercicioKT", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EjercicioKT", "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d("EjercicioKT", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("EjercicioKT", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EjercicioKT", "onDestroy")
    }
}