package com.servicios.app.posts

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servicios.app.databinding.FragmentServicePostsFeedBinding
import com.servicios.app.databinding.ItemPublicacionBinding

/**
 * Feed de publicaciones de servicios.
 */
class ServicePostsFeedFragment : Fragment() {

    private var _b: FragmentServicePostsFeedBinding? = null
    private val b get() = _b!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentServicePostsFeedBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PublicacionesAdapter(PublicacionesData.publicaciones) { post ->
            val i = Intent(requireContext(), PostDetailActivity::class.java).apply {
                putExtra("post_id", post.id)
                putExtra("post_titulo", post.titulo)
            }
            startActivity(i)
        }

        b.rvPublicaciones.layoutManager = LinearLayoutManager(requireContext())
        b.rvPublicaciones.adapter = adapter

        // FAB para crear nueva publicación
        b.fabCrearPublicacion.setOnClickListener {
            startActivity(Intent(requireContext(), CreateServicePostActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}

class PublicacionesAdapter(
    private val items: List<PublicacionServicio>,
    private val onClick: (PublicacionServicio) -> Unit,
) : RecyclerView.Adapter<PublicacionesAdapter.VH>() {

    inner class VH(val b: ItemPublicacionBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(p: PublicacionServicio) {
            b.tvTitulo.text       = p.titulo
            b.tvCategoria.text    = p.categoria
            b.tvDescripcion.text  = p.descripcion.take(120) + if (p.descripcion.length > 120) "..." else ""
            b.tvPresupuesto.text  = p.presupuesto
            b.tvUbicacion.text    = "🏠 ${p.direccion}, ${p.ciudad}"
            b.tvFecha.text        = p.fechaCreacion
            b.tvOfertas.text      = "${p.ofertasRecibidas} oferta(s)"
            
            b.tvUrgencia.text = when (p.urgencia) {
                Urgencia.URGENTE     -> "Urgente"
                Urgencia.HOY         -> "Hoy"
                Urgencia.ESTA_SEMANA -> "Esta semana"
                Urgencia.SIN_PRISA   -> "Sin prisa"
            }
            b.root.setOnClickListener { onClick(p) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemPublicacionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
