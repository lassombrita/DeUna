package com.servicios.app.posts

import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.servicios.app.databinding.ActivityCreateServicePostBinding

/**
 * Pantalla: Crear publicación de servicio
 */
class CreateServicePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateServicePostBinding
    private val fotosSeleccionadas = mutableListOf<Uri>()

    private val pickImages = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        val nuevas = uris.take(4 - fotosSeleccionadas.size)
        fotosSeleccionadas.addAll(nuevas)
        binding.tvFotosSeleccionadas.text = "${fotosSeleccionadas.size} foto(s) seleccionada(s)"
    }

    private val categorias = listOf(
        "Plomería", "Electricidad", "Cerrajería", "Aseo",
        "Pintura", "Gas", "Carpintería", "Tecnología",
        "Jardinería", "Mudanzas", "Albañilería", "Otro"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateServicePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        // ─── Spinner de categorías ─────────────────────────────
        val spinnerAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, categorias
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerCategoria.adapter = spinnerAdapter

        // ─── Adjuntar fotos ────────────────────────────────────
        binding.btnAdjuntarFotos.setOnClickListener {
            if (fotosSeleccionadas.size >= 4) {
                Toast.makeText(this, "Máximo 4 fotos", Toast.LENGTH_SHORT).show()
            } else {
                pickImages.launch("image/*")
            }
        }

        // ─── Publicar ─────────────────────────────────────────
        binding.btnPublicar.setOnClickListener {
            val titulo       = binding.etTitulo.text.toString().trim()
            val descripcion  = binding.etDescripcion.text.toString().trim()
            val presupMin    = binding.etPresupuestoMin.text.toString().trim()
            val presupMax    = binding.etPresupuestoMax.text.toString().trim()

            if (titulo.length < 10) {
                Toast.makeText(this, "El título debe tener al menos 10 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (descripcion.length < 30) {
                Toast.makeText(this, "Describe el servicio con más detalle (mín. 30 chars)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val urgencia = when (binding.rgUrgencia.checkedRadioButtonId) {
                binding.rbUrgente.id   -> Urgencia.URGENTE
                binding.rbHoy.id       -> Urgencia.HOY
                binding.rbSemana.id    -> Urgencia.ESTA_SEMANA
                else                   -> Urgencia.SIN_PRISA
            }

            val publicacion = PublicacionServicio(
                id              = System.currentTimeMillis().toInt(),
                titulo          = titulo,
                descripcion     = descripcion,
                categoria       = categorias[binding.spinnerCategoria.selectedItemPosition],
                presupuesto     = "\$$presupMin - \$$presupMax",
                urgencia        = urgencia,
                direccion       = binding.etDireccion.text.toString(),
                ciudad          = "Bogotá",
                fechaCreacion   = "Ahora",
                ofertasRecibidas = 0,
                estado          = EstadoPublicacion.ACTIVA,
                autor           = "Laura G.",
            )

            // viewModel.publicarServicio(publicacion, fotosSeleccionadas)
            Toast.makeText(this, "¡Publicación creada! Los profesionales verán tu solicitud.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
