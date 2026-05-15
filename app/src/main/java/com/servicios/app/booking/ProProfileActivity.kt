package com.servicios.app.booking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityProProfileBinding

class ProProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("pro_name") ?: "Profesional"
        val rating = intent.getFloatExtra("pro_rating", 4.8f)
        val badge = intent.getStringExtra("pro_badge") ?: ""
        val precio = intent.getIntExtra("pro_price", 0)

        binding.tvProName.text = nombre
        binding.tvRating.text = "★ $rating"
        binding.tvBadge.text = badge
        binding.tvPrecio.text = "Desde \$${String.format("%,d", precio)}"

        binding.btnBack.setOnClickListener { finish() }

        binding.btnNegociar.setOnClickListener {
            val i = Intent(this, ChatActivity::class.java).apply {
                putExtra("pro_name", nombre)
                putExtra("pro_price", precio)
            }
            startActivity(i)
        }

        binding.btnContratar.setOnClickListener {
            val i = Intent(this, ConfirmServiceActivity::class.java).apply {
                putExtra("pro_name", nombre)
                putExtra("pro_price", precio)
            }
            startActivity(i)
        }
    }
}