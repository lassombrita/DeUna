package com.servicios.app.booking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityConfirmServiceBinding

class ConfirmServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proName = intent.getStringExtra("pro_name") ?: "Profesional"
        val proPrice = intent.getIntExtra("pro_price", 50000)

        binding.tvProName.text = proName
        binding.tvPrecio.text = "\$${String.format("%,d", proPrice)}"
        binding.tvDescripcion.text = "Fuga de agua en el baño"
        binding.tvDireccion.text = "Calle 123 #45-67, Bogotá"
        binding.tvFecha.text = "Hoy, 11:30 a.m."
        binding.tvMetodoPago.text = "Tarjeta terminada en 1234"

        binding.btnBack.setOnClickListener { finish() }

        binding.btnConfirmar.setOnClickListener {
            val i = Intent(this, PagoSeguroActivity::class.java).apply {
                putExtra("pro_name", proName)
                putExtra("pro_price", proPrice)
            }
            startActivity(i)
        }
    }
}