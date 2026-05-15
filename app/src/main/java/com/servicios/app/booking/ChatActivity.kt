package com.servicios.app.booking

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.servicios.app.R
import com.servicios.app.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val proName by lazy { intent.getStringExtra("pro_name") ?: "Profesional" }
    private val proPrice by lazy { intent.getIntExtra("pro_price", 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvProName.text = proName
        binding.btnBack.setOnClickListener { finish() }

        addMessage("¡Hola! Claro que sí. ¿Me puedes enviar una foto del problema?", isUser = false)

        binding.btnSend.setOnClickListener {
            val msg = binding.etMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                addMessage(msg, isUser = true)
                binding.etMessage.text?.clear()
            }
        }

        binding.btnConfirmarPrecio.setOnClickListener {
            val precioTexto = binding.etPrecioAcordado.text.toString().trim()
            if (precioTexto.isNotEmpty()) {
                val precioInt = precioTexto.replace(".", "").replace(",", "").toIntOrNull() ?: proPrice
                val i = Intent(this, ConfirmServiceActivity::class.java).apply {
                    putExtra("pro_name", proName)
                    putExtra("pro_price", precioInt)
                }
                startActivity(i)
            }
        }
    }

    private fun addMessage(text: String, isUser: Boolean) {
        val tv = TextView(this).apply {
            this.text = text
            textSize = 14f
            setPadding(32, 20, 32, 20)
            setTextColor(if (isUser) 0xFFFFFFFF.toInt() else 0xFF1E293B.toInt())
            background = ContextCompat.getDrawable(context,
                if (isUser) R.drawable.bg_bubble_user
                else R.drawable.bg_bubble_pro)
            maxWidth = (resources.displayMetrics.widthPixels * 0.75).toInt()
        }
        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = if (isUser) Gravity.END else Gravity.START
            topMargin = 12; bottomMargin = 12
            marginStart = 24; marginEnd = 24
        }
        binding.llMessages.addView(tv, lp)
        binding.scrollMessages.post {
            binding.scrollMessages.fullScroll(android.widget.ScrollView.FOCUS_DOWN)
        }
    }
}