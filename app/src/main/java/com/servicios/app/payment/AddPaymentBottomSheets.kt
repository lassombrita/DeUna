package com.servicios.app.payment

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.servicios.app.databinding.BottomSheetAddBankBinding
import com.servicios.app.databinding.BottomSheetAddCardBinding
import com.servicios.app.databinding.BottomSheetAddPseBinding

class AddCardBottomSheet : BottomSheetDialogFragment() {
    private var _b: BottomSheetAddCardBinding? = null
    private val b get() = _b!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = BottomSheetAddCardBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        b.btnGuardar.setOnClickListener {
            val numero = b.etNumeroTarjeta.text.toString().replace(" ","")
            val titular = b.etTitular.text.toString()
            val vence = b.etVencimiento.text.toString()
            val cvv = b.etCvv.text.toString()

            if (numero.length < 16 || titular.isEmpty() || vence.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(requireContext(), "Tarjeta guardada correctamente", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _b = null }
    companion object { fun newInstance() = AddCardBottomSheet() }
}

class AddPSEBottomSheet : BottomSheetDialogFragment() {
    private val bancos = listOf(
        "Bancolombia", "Davivienda", "BBVA", "Banco de Bogotá",
        "Nequi", "Banco Popular", "Banco de Occidente",
        "AV Villas", "Bancoomeva", "Banco Pichincha",
        "Banco Caja Social", "Banco Falabella", "Banco Finandina",
    )
    private var _b: BottomSheetAddPseBinding? = null
    private val b get() = _b!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = BottomSheetAddPseBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bancos)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        b.spinnerBanco.adapter = spinnerAdapter

        b.btnVincularPSE.setOnClickListener {
            Toast.makeText(requireContext(), "Redirigiendo a tu banco para autorizar...", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _b = null }
    companion object { fun newInstance() = AddPSEBottomSheet() }
}

class AddBankBottomSheet : BottomSheetDialogFragment() {
    private var _b: BottomSheetAddBankBinding? = null
    private val b get() = _b!!
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = BottomSheetAddBankBinding.inflate(i, c, false); return b.root
    }
    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        b.btnVincular.setOnClickListener {
            Toast.makeText(requireContext(), "Cuenta bancaria vinculada", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _b = null }
    companion object { fun newInstance() = AddBankBottomSheet() }
}
