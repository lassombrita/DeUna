package com.servicios.app.profile

data class Direccion(
    val id: Int,
    val alias: String,             // "Casa", "Trabajo", "Mamá"
    val direccionCompleta: String,
    val barrio: String,
    val ciudad: String,
    val esActual: Boolean = false, // es la del header de inicio
    val icono: String,             // "🏠", "🏠", "🏠"
)
