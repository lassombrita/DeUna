package com.servicios.app.messages

/** Representa una conversación en la lista de chats */
data class Conversacion(
    val id: Int,
    val proNombre: String,
    val proEspecialidad: String,
    val proAvatarUrl: String,
    val ultimoMensaje: String,
    val hora: String,
    val mensajesNoLeidos: Int,
    val estado: EstadoConversacion,
)

enum class EstadoConversacion { EN_PROGRESO, COMPLETADO, CANCELADO }

/** Representa un mensaje individual dentro de la conversación */
data class Mensaje(
    val texto: String,
    val hora: String,
    val esUsuario: Boolean,       // true = azul derecha / false = blanco izquierda
    val imagenUrl: String? = null // adjunto de foto (opcional)
)
