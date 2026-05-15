package com.servicios.app.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.servicios.app.auth.SessionManager
import com.servicios.app.databinding.BottomSheetPerfilSettingsBinding
import com.servicios.app.databinding.ItemSesionBinding
import com.servicios.app.settings.ConfiguracionActivity

/**
 * BottomSheet que aparece al tocar la tuerca ⚙ en el perfil.
 * Muestra: sesiones guardadas, cambiar cuenta, configuración y cerrar sesión.
 */
class PerfilSettingsBottomSheet : BottomSheetDialogFragment() {

    private var _b: BottomSheetPerfilSettingsBinding? = null
    private val b get() = _b!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = BottomSheetPerfilSettingsBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = requireContext()

        // Precargar sesiones de ejemplo la primera vez
        SessionManager.precargarSesionesEjemplo(ctx)

        val sesiones = SessionManager.getSesiones(ctx)
        val actual   = SessionManager.getSesionActual(ctx)

        // Lista de sesiones guardadas
        b.rvSesiones.layoutManager = LinearLayoutManager(ctx)
        b.rvSesiones.adapter = SesionesAdapter(sesiones, actual) { sesion ->
            // Cambiar a la sesión seleccionada
            SessionManager.setSesionActual(ctx, sesion)
            dismiss()
            // Refrescar el fragment de perfil
            activity?.recreate()
        }

        // Agregar nueva cuenta
        b.btnAgregarCuenta.setOnClickListener {
            dismiss()
            // Redirigir a login para agregar cuenta (si existiera)
            // val intent = Intent(ctx, LoginActivity::class.java).apply { putExtra("modo", "nueva_cuenta") }
            // startActivity(intent)
        }

        // Configuración
        b.rowConfiguracion.setOnClickListener {
            dismiss()
            startActivity(Intent(ctx, ConfiguracionActivity::class.java))
        }

        // Cerrar sesión
        b.btnCerrarSesion.setOnClickListener {
            android.app.AlertDialog.Builder(ctx)
                .setTitle("Cerrar sesión")
                .setMessage("¿Seguro que quieres cerrar sesión?")
                .setPositiveButton("Sí") { _, _ ->
                    SessionManager.cerrarSesion(ctx)
                    dismiss()
                    // Redirigir a Login o Home
                    activity?.finish()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    companion object {
        fun newInstance() = PerfilSettingsBottomSheet()
    }
}

class SesionesAdapter(
    private val items: List<SessionManager.Sesion>,
    private val actual: SessionManager.Sesion?,
    private val onClick: (SessionManager.Sesion) -> Unit,
) : RecyclerView.Adapter<SesionesAdapter.VH>() {

    inner class VH(val b: ItemSesionBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(s: SessionManager.Sesion) {
            b.tvNombre.text = s.nombre
            b.tvEmail.text = s.email
            b.ivCheck.visibility = if (s.id == actual?.id) View.VISIBLE else View.GONE
            b.root.setOnClickListener { onClick(s) }
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemSesionBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, pos: Int) = h.bind(items[pos])

    override fun getItemCount() = items.size
}
