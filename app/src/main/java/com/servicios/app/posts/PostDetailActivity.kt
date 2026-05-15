package com.servicios.app.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityChatDetailBinding // Reusing similar layout for detail placeholder

class PostDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val titulo = intent.getStringExtra("post_titulo") ?: "Detalle del Post"
        binding.tvTitle.text = titulo
        binding.btnBack.setOnClickListener { finish() }
    }
}
