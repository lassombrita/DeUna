package com.servicios.app.messages

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servicios.app.databinding.FragmentMensajesBinding
import com.servicios.app.databinding.ItemConversacionBinding

class MensajesFragment : Fragment() {

    private var _binding: FragmentMensajesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMensajesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ConversacionesAdapter(ConversacionesData.conversaciones) { conv ->
            // Abrir ChatDetailActivity con los mensajes de esa conversación
            val intent = Intent(requireContext(), ChatDetailActivity::class.java).apply {
                putExtra("conv_id",  conv.id)
                putExtra("pro_name", conv.proNombre)
                putExtra("pro_esp",  conv.proEspecialidad)
            }
            startActivity(intent)
        }

        binding.rvConversaciones.layoutManager = LinearLayoutManager(requireContext())
        binding.rvConversaciones.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Adapter de la lista de chats
class ConversacionesAdapter(
    private val items: List<Conversacion>,
    private val onClick: (Conversacion) -> Unit,
) : RecyclerView.Adapter<ConversacionesAdapter.VH>() {

    inner class VH(val b: ItemConversacionBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(c: Conversacion) {
            b.tvNombre.text        = c.proNombre
            b.tvEspecialidad.text  = c.proEspecialidad
            b.tvUltimoMensaje.text = c.ultimoMensaje
            b.tvHora.text          = c.hora
            // Badge de mensajes no leídos
            b.tvBadge.visibility =
                if (c.mensajesNoLeidos > 0) android.view.View.VISIBLE
                else android.view.View.GONE
            b.tvBadge.text = c.mensajesNoLeidos.toString()
            // Color del estado
            b.tvEstado.text = when(c.estado) {
                EstadoConversacion.EN_PROGRESO -> "En progreso"
                EstadoConversacion.COMPLETADO  -> "Completado"
                EstadoConversacion.CANCELADO   -> "Cancelado"
            }
            b.tvEstado.setTextColor(when(c.estado) {
                EstadoConversacion.EN_PROGRESO -> 0xFF2563EB.toInt()
                EstadoConversacion.COMPLETADO  -> 0xFF16A34A.toInt()
                EstadoConversacion.CANCELADO   -> 0xFFDC2626.toInt()
            })
            b.root.setOnClickListener { onClick(c) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemConversacionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
