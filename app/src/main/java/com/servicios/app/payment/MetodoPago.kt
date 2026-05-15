package com.servicios.app.payment

sealed class MetodoPago {
    data class Tarjeta(
        val id: String,
        val ultimos4: String,
        val marca: MarcaTarjeta,
        val titular: String,
        val vencimiento: String,
        val esPrincipal: Boolean = false,
    ) : MetodoPago()

    data class PSE(
        val id: String,
        val banco: String,
        val tipoCuenta: String,    // "Ahorros" o "Corriente"
        val ultimosDigitos: String,
    ) : MetodoPago()

    data class PayPal(
        val id: String,
        val email: String,
    ) : MetodoPago()

    data class CuentaBancaria(
        val id: String,
        val banco: String,
        val tipoCuenta: String,
        val numeroCuenta: String,
        val titular: String,
    ) : MetodoPago()
}

enum class MarcaTarjeta { VISA, MASTERCARD, AMEX }
