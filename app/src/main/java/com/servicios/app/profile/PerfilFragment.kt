package com.servicios.app.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.servicios.app.databinding.FragmentPerfilBinding
import com.servicios.app.payment.PaymentMethodsActivity

class PerfilFragment : Fragment() {
    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.tvNombreUsuario.text = "Hola, Laura 👋"
        binding.tvEmail.text = "laura@email.com"
        binding.tvSaldo.text = "\$120.000"

        binding.btnRecargar.setOnClickListener {
            Toast.makeText(requireContext(), "Próximamente: recarga de saldo", Toast.LENGTH_SHORT).show()
        }

        binding.iconEnProgreso.setOnClickListener {
            // Navigation logic here
        }

        binding.rowMetodosPago.setOnClickListener {
            startActivity(Intent(requireContext(), PaymentMethodsActivity::class.java))
        }

        binding.rowDirecciones.setOnClickListener {
            startActivity(Intent(requireContext(), AddressesActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            PerfilSettingsBottomSheet.newInstance().show(parentFragmentManager, "settings")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}