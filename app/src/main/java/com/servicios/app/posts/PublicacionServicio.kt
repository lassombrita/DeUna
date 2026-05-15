package com.servicios.app.posts

data class PublicacionServicio(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val categoria: String,
    val presupuesto: String,
    val urgencia: Urgencia,
    val direccion: String,
    val ciudad: String,
    val fechaCreacion: String,
    val ofertasRecibidas: Int,
    val estado: EstadoPublicacion,
    val autor: String,
    val fotoUrl: String = "",
)

enum class Urgencia { URGENTE, HOY, ESTA_SEMANA, SIN_PRISA }
enum class EstadoPublicacion { ACTIVA, EN_PROCESO, FINALIZADA, CANCELADA }
