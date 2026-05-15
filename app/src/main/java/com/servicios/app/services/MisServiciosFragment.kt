package com.servicios.app.services

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.servicios.app.R
import com.servicios.app.booking.ServiceInProgressActivity
import com.servicios.app.databinding.FragmentMisServiciosBinding
import com.servicios.app.posts.ServicePostsFeedFragment

class MisServiciosFragment : Fragment() {
    private var _binding: FragmentMisServiciosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMisServiciosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tabsMisServicios.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showEnProgreso()
                    1 -> showHistorial()
                    2 -> showCancelados()
                    3 -> showPublicaciones()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        showEnProgreso()

        binding.btnVerProgreso.setOnClickListener {
            val i = Intent(requireContext(), ServiceInProgressActivity::class.java).apply {
                putExtra("pro_name", "Juan Carlos López")
                putExtra("pro_price", 50000)
            }
            startActivity(i)
        }
    }

    private fun showEnProgreso() {
        binding.layoutEnProgreso.visibility = View.VISIBLE
        binding.layoutHistorial.visibility = View.GONE
        binding.layoutCancelados.visibility = View.GONE
        binding.layoutPublicaciones.visibility = View.GONE
    }

    private fun showHistorial() {
        binding.layoutEnProgreso.visibility = View.GONE
        binding.layoutHistorial.visibility = View.VISIBLE
        binding.layoutCancelados.visibility = View.GONE
        binding.layoutPublicaciones.visibility = View.GONE
    }

    private fun showCancelados() {
        binding.layoutEnProgreso.visibility = View.GONE
        binding.layoutHistorial.visibility = View.GONE
        binding.layoutCancelados.visibility = View.VISIBLE
        binding.layoutPublicaciones.visibility = View.GONE
    }

    private fun showPublicaciones() {
        binding.layoutEnProgreso.visibility = View.GONE
        binding.layoutHistorial.visibility = View.GONE
        binding.layoutCancelados.visibility = View.GONE
        binding.layoutPublicaciones.visibility = View.VISIBLE
        
        childFragmentManager.beginTransaction()
            .replace(R.id.layoutPublicaciones, ServicePostsFeedFragment())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
