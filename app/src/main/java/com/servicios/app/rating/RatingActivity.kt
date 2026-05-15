package com.servicios.app.rating

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.R
import com.servicios.app.databinding.ActivityRatingBinding

class RatingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRatingBinding
    private var selectedRating = 0
    private var recomienda: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proName = intent.getStringExtra("pro_name") ?: "Profesional"
        binding.tvProName.text = proName

        val stars = listOf(
            binding.star1, binding.star2, binding.star3,
            binding.star4, binding.star5
        )

        stars.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                selectedRating = index + 1
                stars.forEachIndexed { i, s ->
                    s.setImageResource(
                        if (i < selectedRating) R.drawable.ic_star_filled
                        else R.drawable.ic_star_empty
                    )
                }
            }
        }

        binding.btnSiRecomienda.setOnClickListener {
            recomienda = true
            binding.btnSiRecomienda.alpha = 1f
            binding.btnNoRecomienda.alpha = 0.4f
        }

        binding.btnNoRecomienda.setOnClickListener {
            recomienda = false
            binding.btnSiRecomienda.alpha = 0.4f
            binding.btnNoRecomienda.alpha = 1f
        }

        binding.btnEnviar.setOnClickListener {
            if (selectedRating == 0) {
                Toast.makeText(this, "Selecciona al menos 1 estrella", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "¡Gracias por tu calificación!", Toast.LENGTH_LONG).show()
            finishAffinity()
        }
    }
}