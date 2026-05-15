package com.servicios.app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servicios.app.databinding.ActivityAddressesBinding
import com.servicios.app.databinding.ItemDireccionBinding

class AddressesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        val adapter = DireccionesAdapter(DireccionesData.direcciones) { dir ->
            // Al seleccionar una dirección → actualizar el header de inicio (simulado con SharedPreferences)
            val prefs = getSharedPreferences("tuhogar_prefs", MODE_PRIVATE)
            prefs.edit()
                .putString("ciudad_header", dir.ciudad)
                .putString("dir_actual_id", dir.id.toString())
                .apply()
            Toast.makeText(this, "Ubicación actualizada a: ${dir.alias}", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.rvDirecciones.layoutManager = LinearLayoutManager(this)
        binding.rvDirecciones.adapter = adapter

        // Botón agregar nueva dirección
        binding.btnAgregarDireccion.setOnClickListener {
            Toast.makeText(this, "Próximamente: selecciona en el mapa", Toast.LENGTH_SHORT).show()
        }
    }
}

class DireccionesAdapter(
    private val items: List<Direccion>,
    private val onSelect: (Direccion) -> Unit,
) : RecyclerView.Adapter<DireccionesAdapter.VH>() {

    inner class VH(val b: ItemDireccionBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(d: Direccion) {
            b.tvIcono.text    = d.icono
            b.tvAlias.text    = d.alias
            b.tvDireccion.text = d.direccionCompleta
            b.tvBarrioCiudad.text = "${d.barrio} · ${d.ciudad}"
            // Badge 'Actual' si es la dirección vigente del header
            b.chipActual.visibility =
                if (d.esActual) android.view.View.VISIBLE
                else android.view.View.GONE
            b.root.setOnClickListener { onSelect(d) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemDireccionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
