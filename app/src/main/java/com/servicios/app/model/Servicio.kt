package com.servicios.app.model

import androidx.annotation.DrawableRes

data class Servicio(
    val id: String,
    val nombre: String,
    @get:DrawableRes val iconRes: Int = 0,
    val precio: Int = 0,
    val imageUrl: String? = null,
    val esVerMas: Boolean = false
)
