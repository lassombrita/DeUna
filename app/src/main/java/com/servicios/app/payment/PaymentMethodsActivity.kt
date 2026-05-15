package com.servicios.app.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.servicios.app.databinding.ActivityPaymentMethodsBinding
import com.servicios.app.databinding.ItemMetodoPagoBinding

class PaymentMethodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        val adapter = MetodoPagoAdapter(MetodosPagoData.metodos) { metodo ->
            Toast.makeText(this, "Método seleccionado", Toast.LENGTH_SHORT).show()
        }

        binding.rvMetodosPago.layoutManager = LinearLayoutManager(this)
        binding.rvMetodosPago.adapter = adapter

        // ─── Botones para agregar nuevo método ────────────────

        binding.btnAgregarTarjeta.setOnClickListener {
            AddCardBottomSheet.newInstance().show(supportFragmentManager, "add_card")
        }

        binding.btnAgregarPSE.setOnClickListener {
            AddPSEBottomSheet.newInstance().show(supportFragmentManager, "add_pse")
        }

        binding.btnAgregarPayPal.setOnClickListener {
            Toast.makeText(this, "Redirigiendo a PayPal para vincular cuenta...", Toast.LENGTH_SHORT).show()
        }

        binding.btnAgregarBanco.setOnClickListener {
            AddBankBottomSheet.newInstance().show(supportFragmentManager, "add_bank")
        }
    }
}

class MetodoPagoAdapter(
    private val items: List<MetodoPago>,
    private val onSelect: (MetodoPago) -> Unit,
) : RecyclerView.Adapter<MetodoPagoAdapter.VH>() {

    inner class VH(val b: ItemMetodoPagoBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(m: MetodoPago) {
            when (m) {
                is MetodoPago.Tarjeta -> {
                    b.tvIcono.text = when(m.marca) {
                        MarcaTarjeta.VISA -> "VISA"
                        MarcaTarjeta.MASTERCARD -> "MC"
                        MarcaTarjeta.AMEX -> "AMEX"
                    }
                    b.tvPrincipal.text = "•••• ${m.ultimos4}"
                    b.tvSecundario.text = "${m.titular} · Vence ${m.vencimiento}"
                    b.chipPrincipal.visibility = if (m.esPrincipal) android.view.View.VISIBLE else android.view.View.GONE
                }
                is MetodoPago.PSE -> {
                    b.tvIcono.text = "PSE"
                    b.tvPrincipal.text = m.banco
                    b.tvSecundario.text = "${m.tipoCuenta} · ••••${m.ultimosDigitos}"
                    b.chipPrincipal.visibility = android.view.View.GONE
                }
                is MetodoPago.PayPal -> {
                    b.tvIcono.text = "PayPal"
                    b.tvPrincipal.text = "PayPal"
                    b.tvSecundario.text = m.email
                    b.chipPrincipal.visibility = android.view.View.GONE
                }
                is MetodoPago.CuentaBancaria -> {
                    b.tvIcono.text = "Banco"
                    b.tvPrincipal.text = m.banco
                    b.tvSecundario.text = "${m.tipoCuenta} · ${m.numeroCuenta}"
                    b.chipPrincipal.visibility = android.view.View.GONE
                }
            }
            b.root.setOnClickListener { onSelect(m) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemMetodoPagoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
