package com.servicios.app.booking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityPagoSeguroBinding

class PagoSeguroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagoSeguroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoSeguroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proName = intent.getStringExtra("pro_name") ?: "Profesional"
        val precio = intent.getIntExtra("pro_price", 50000)
        val comision = (precio * 0.10).toInt()
        val total = precio + comision

        binding.tvServicio.text = "\$${String.format("%,d", precio)}"
        binding.tvComision.text = "\$${String.format("%,d", comision)}"
        binding.tvTotal.text = "\$${String.format("%,d", total)}"
        binding.tvProName.text = proName

        binding.btnBack.setOnClickListener { finish() }

        binding.btnEntendido.setOnClickListener {
            val i = Intent(this, ServiceInProgressActivity::class.java).apply {
                putExtra("pro_name", proName)
                putExtra("pro_price", precio)
            }
            startActivity(i)
            finishAffinity()
        }
    }
}