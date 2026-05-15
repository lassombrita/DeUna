package com.servicios.app.pro

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityValidationBinding

class ValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidationBinding
    private var selectedLevel = 1

    private val pickFile = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onFileSelected(it) }
    }

    private var pendingFileField = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        binding.btnNivel1.setOnClickListener { selectLevel(1) }
        binding.btnNivel2.setOnClickListener { selectLevel(2) }
        binding.btnNivel3.setOnClickListener { selectLevel(3) }

        selectLevel(1)

        binding.btnSubirCedula.setOnClickListener {
            pendingFileField = "cedula"
            pickFile.launch("image/*")
        }

        binding.btnGrabarVideo.setOnClickListener {
            Toast.makeText(this, "Abriendo cámara de video...", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubirCertificado.setOnClickListener {
            pendingFileField = "certificado"
            pickFile.launch("application/pdf")
        }

        binding.btnSubirTitulo.setOnClickListener {
            pendingFileField = "titulo"
            pickFile.launch("application/pdf")
        }

        binding.btnEnviarRevision.setOnClickListener {
            if (!binding.cbAutorizo.isChecked) {
                Toast.makeText(this, "Debes autorizar la verificación", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Solicitud enviada. Revisaremos en 24-48 horas.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun selectLevel(level: Int) {
        selectedLevel = level
        val btnColor = 0xFF2563EB.toInt()
        val idleColor = 0xFFE2E8F0.toInt()
        binding.btnNivel1.setBackgroundColor(if (level == 1) btnColor else idleColor)
        binding.btnNivel2.setBackgroundColor(if (level == 2) btnColor else idleColor)
        binding.btnNivel3.setBackgroundColor(if (level == 3) btnColor else idleColor)

        binding.tvNivelLabel.text = when (level) {
            1 -> "Nivel 1 — Empírico"
            2 -> "Nivel 2 — Técnico certificado"
            3 -> "Nivel 3 — Profesional formal"
            else -> ""
        }
        binding.tvNivelDesc.text = when (level) {
            1 -> "Para personas con experiencia práctica aprendida en el transcurso de su vida."
            2 -> "Para personas con estudios técnicos, cursos o certificaciones."
            3 -> "Para profesionales con título universitario y matrícula profesional."
            else -> ""
        }

        binding.layoutNivel1Docs.visibility = if (level == 1) View.VISIBLE else View.GONE
        binding.layoutNivel2Docs.visibility = if (level == 2) View.VISIBLE else View.GONE
        binding.layoutNivel3Docs.visibility = if (level == 3) View.VISIBLE else View.GONE
    }

    private fun onFileSelected(uri: Uri) {
        val name = uri.lastPathSegment ?: "archivo seleccionado"
        when (pendingFileField) {
            "cedula" -> binding.tvCedulaFile.text = name
            "certificado" -> binding.tvCertificadoFile.text = name
            "titulo" -> binding.tvTituloFile.text = name
        }
        Toast.makeText(this, "Archivo cargado: $name", Toast.LENGTH_SHORT).show()
    }
}