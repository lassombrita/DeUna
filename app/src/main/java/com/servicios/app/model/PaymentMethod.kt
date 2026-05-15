package com.servicios.app.model

data class PaymentMethod(
    val id: String,
    val name: String,
    val iconRes: Int? = null,
    val type: PaymentType
)

enum class PaymentType {
    NEQUI, DAVIPLATA, PSE, CREDIT_CARD
}
