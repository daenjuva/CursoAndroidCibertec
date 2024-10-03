package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Se utiliza ViewBinding para acceder a las vistas definidas en el archivo XML correspondiente
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño utilizando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge() // Habilita el diseño de bordes
        setContentView(binding.root) // Establece el contenido de la vista como la raíz inflada

        // Ajuste de las barras de sistema (estado, navegación) para que no solapen el contenido
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura el botón "Calcular"
        binding.btnCalcular.setOnClickListener {
            // Muestra un mensaje con el nombre y el DNI ingresados
            val textToShow = "${binding.etNombre.text}: ${binding.etDni.text}"
            Utils.showToast(this@MainActivity, textToShow)

            var servicio = 0.0
            var instalacion = 0.0
            var descuento = 0.0
            var total = 0.0

            // Calcula el total dependiendo del tipo de servicio seleccionado
            total = when {
                binding.rbDuo.isChecked -> { // Si se selecciona el servicio Dúo
                    servicio = 109.0
                    instalacion = 35.0
                    descuento = servicio * 0.05
                    servicio + instalacion - descuento
                }
                binding.rbTrio.isChecked -> { // Si se selecciona el servicio Trío
                    servicio = 159.0
                    instalacion = 60.0
                    descuento = servicio * 0.1
                    servicio + instalacion - descuento
                }
                binding.rbInter.isChecked -> { // Si se selecciona solo Internet
                    servicio = 89.0
                    instalacion = 15.0
                    descuento = servicio * 0.02
                    servicio + instalacion - descuento
                }
                binding.rbTlf.isChecked -> { // Si se selecciona solo Teléfono
                    servicio = 59.0
                    instalacion = 12.0
                    descuento = servicio * 0.01
                    servicio + instalacion - descuento
                }
                binding.rbCable.isChecked -> { // Si se selecciona solo Cable
                    servicio = 79.0
                    instalacion = 15.0
                    descuento = servicio * 0.01
                    servicio + instalacion - descuento
                }
                else -> 0.0  // Si no se selecciona ningún plan
            }

            // Muestra el resultado calculado en los campos de la interfaz
            binding.etServicio.setText(servicio.toString())  // Muestra el valor del servicio
            binding.etInstal.setText(instalacion.toString()) // Muestra el costo de instalación
            binding.etDto.setText(descuento.toString())      // Muestra el descuento aplicado
            binding.etTotal.setText(total.toString())        // Muestra el total a pagar
        }

        // Configura el botón "Imprimir"
        binding.btnImprimir.setOnClickListener {
            // Muestra un mensaje con el nombre y el DNI ingresados
            val textToShow = "${binding.etNombre.text}: ${binding.etDni.text}"
            Utils.showToast(this@MainActivity, textToShow)

            // Recopila los valores de entrada
            val nombre = binding.etNombre.text.toString()
            val dni = binding.etDni.text.toString()
            var servicio = 0.0
            var instalacion = 0.0
            var descuento = 0.0
            var plan = "" // Descripción del plan seleccionado

            // Calcula el total dependiendo del tipo de servicio seleccionado
            val total = when {
                binding.rbDuo.isChecked -> { // Si se selecciona el servicio Dúo
                    plan = "Dúo - Teléfono e Internet"
                    servicio = 109.0
                    instalacion = 35.0
                    descuento = servicio * 0.05
                    servicio + instalacion - descuento
                }
                binding.rbTrio.isChecked -> { // Si se selecciona el servicio Trío
                    plan = "Trio - Teléfono + TV + Internet"
                    servicio = 159.0
                    instalacion = 60.0
                    descuento = servicio * 0.1
                    servicio + instalacion - descuento
                }
                binding.rbInter.isChecked -> { // Si se selecciona solo Internet
                    plan = "Solo Internet"
                    servicio = 89.0
                    instalacion = 15.0
                    descuento = servicio * 0.02
                    servicio + instalacion - descuento
                }
                binding.rbTlf.isChecked -> { // Si se selecciona solo Teléfono
                    plan = "Solo Teléfono"
                    servicio = 59.0
                    instalacion = 12.0
                    descuento = servicio * 0.01
                    servicio + instalacion - descuento
                }
                binding.rbCable.isChecked -> { // Si se selecciona solo Cable
                    plan = "Solo Cable"
                    servicio = 79.0
                    instalacion = 15.0
                    descuento = servicio * 0.01
                    servicio + instalacion - descuento
                }
                else -> 0.0  // Si no se selecciona ningún servicio
            }

            // Crea un Intent para navegar a la pantalla "Imprimir" con los datos calculados
            val newView = Intent(this, Imprimir::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("dni", dni)
                putExtra("plan", plan)
                putExtra("servicio", servicio.toString())
                putExtra("instalacion", instalacion.toString())
                putExtra("descuento", descuento.toString())
                putExtra("total", total.toString())
            }
            startActivity(newView) // Navega a la nueva actividad
        }

        // Configura el botón "Limpiar"
        binding.btnLimpiar.setOnClickListener {
            // Limpia todos los campos de texto
            binding.etNombre.text.clear()
            binding.etDni.text.clear()
            binding.etServicio.text.clear()
            binding.etInstal.text.clear()
            binding.etDto.text.clear()
            binding.etTotal.text.clear()

            // Desmarca todos los RadioButtons
            binding.rbGroup.clearCheck()
        }
    }
}
