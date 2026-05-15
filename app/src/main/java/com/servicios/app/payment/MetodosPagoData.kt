package com.servicios.app.payment

object MetodosPagoData {
    val metodos: List<MetodoPago> = listOf(
        MetodoPago.Tarjeta("t1", "1234", MarcaTarjeta.VISA,
            "Laura García", "12/26", esPrincipal = true),
        MetodoPago.Tarjeta("t2", "5678", MarcaTarjeta.MASTERCARD,
            "Laura García", "08/25"),
        MetodoPago.PSE("pse1", "Bancolombia",
            "Ahorros", "4567"),
        MetodoPago.PayPal("pp1", "laura@email.com"),
        MetodoPago.CuentaBancaria("cb1", "Davivienda",
            "Corriente", "***-***-8901", "Laura García"),
    )
}
