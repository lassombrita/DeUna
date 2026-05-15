package com.servicios.app.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.servicios.app.R
import com.servicios.app.booking.ProProfileActivity
import com.servicios.app.databinding.ActivityServiceListBinding
import com.servicios.app.databinding.ItemProfesionalBinding

class ServiceListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceListBinding

    private val profesionales = listOf(
        Profesional("Juan Carlos López", 4.8f, 120, "Profesional verificado", 15, 20, 60000),
        Profesional("Miguel Ángel T.", 4.9f, 89, "Certificado", 20, 25, 55000),
        Profesional("Roberto Sánchez", 5.0f, 230, "Experto por experiencia", 10, 15, 70000),
        Profesional("Andrés Gómez", 4.7f, 75, "Experto por experiencia", 25, 30, 50000),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category") ?: "Servicios"
        binding.tvToolbarTitle.text = category

        binding.btnBack.setOnClickListener { finish() }

        binding.rvProfesionales.layoutManager = LinearLayoutManager(this)
        val adapter = ProfesionalAdapter(profesionales) { pro ->
            val intent = Intent(this, ProProfileActivity::class.java).apply {
                putExtra("pro_name", pro.nombre)
                putExtra("pro_rating", pro.calificacion)
                putExtra("pro_badge", pro.badge)
                putExtra("pro_price", pro.precioDesde)
            }
            startActivity(intent)
        }
        binding.rvProfesionales.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> adapter.updateList(profesionales.sortedByDescending { it.calificacion })
                    1 -> adapter.updateList(profesionales.sortedBy { it.tiempoLlegadaMin })
                    2 -> adapter.updateList(profesionales.sortedBy { it.precioDesde })
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}

data class Profesional(
    val nombre: String,
    val calificacion: Float,
    val servicios: Int,
    val badge: String,
    val tiempoLlegadaMin: Int,
    val tiempoLlegadaMax: Int,
    val precioDesde: Int,
)

class ProfesionalAdapter(
    private var items: List<Profesional>,
    private val onClick: (Profesional) -> Unit,
) : RecyclerView.Adapter<ProfesionalAdapter.VH>() {

    fun updateList(newItems: List<Profesional>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemProfesionalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(p: Profesional) {
            binding.tvProName.text = p.nombre
            binding.tvRating.text = "★ ${p.calificacion}"
            binding.tvBadge.text = p.badge
            binding.tvPrice.text = "Desde \$${String.format("%,d", p.precioDesde)}"
            binding.root.setOnClickListener { onClick(p) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProfesionalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}