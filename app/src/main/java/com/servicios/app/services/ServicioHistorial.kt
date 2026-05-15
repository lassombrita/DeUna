package com.servicios.app.services

data class ServicioHistorial(
    val id: Int,
    val profesional: String,
    val especialidad: String,
    val descripcion: String,
    val fecha: String,
    val precio: String,
    val calificacion: Float,
    val estado: EstadoServicio,
    val direccion: String,
)

enum class EstadoServicio { EN_PROGRESO, COMPLETADO, CANCELADO }
