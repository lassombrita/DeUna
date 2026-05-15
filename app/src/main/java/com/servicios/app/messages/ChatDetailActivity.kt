package com.servicios.app.messages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityChatDetailBinding

class ChatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proName = intent.getStringExtra("pro_name") ?: "Profesional"
        binding.tvTitle.text = proName

        binding.btnBack.setOnClickListener { finish() }
    }
}
