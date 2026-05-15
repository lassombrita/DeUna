package com.servicios.app.messages

object ConversacionesData {

    /** Lista de chats que aparece en la pestaña Mensajes */
    val conversaciones = listOf(

        // Chat 1 — Plomería activa (en progreso)
        Conversacion(
            id = 1,
            proNombre = "Juan Carlos López",
            proEspecialidad = "Plomería",
            proAvatarUrl = "",
            ultimoMensaje = "Te lo dejo en $45.000. ¡Allí te veo!",
            hora = "10:34 a.m.",
            mensajesNoLeidos = 0,
            estado = EstadoConversacion.EN_PROGRESO
        ),

        // Chat 2 — Electricidad completada
        Conversacion(
            id = 2,
            proNombre = "Miguel Ángel T.",
            proEspecialidad = "Electricidad",
            proAvatarUrl = "",
            ultimoMensaje = "Servicio finalizado. ¡Hasta pronto!",
            hora = "Ayer",
            mensajesNoLeidos = 0,
            estado = EstadoConversacion.COMPLETADO
        ),

        // Chat 3 — Carpintería con mensaje sin leer
        Conversacion(
            id = 3,
            proNombre = "Roberto Sánchez",
            proEspecialidad = "Carpintería",
            proAvatarUrl = "",
            ultimoMensaje = "¿Cuándo puedo ir a revisar el trabajo?",
            hora = "Lun",
            mensajesNoLeidos = 2,
            estado = EstadoConversacion.COMPLETADO
        ),

        // Chat 4 — Cerrajería cancelada
        Conversacion(
            id = 4,
            proNombre = "Andrés Gómez",
            proEspecialidad = "Cerrajería",
            proAvatarUrl = "",
            ultimoMensaje = "Entendido, cancelamos el servicio.",
            hora = "12 Abr",
            mensajesNoLeidos = 0,
            estado = EstadoConversacion.CANCELADO
        ),
    )

    /** Mensajes por conversación (mapa de id → lista de mensajes) */
    val mensajesPorConversacion = mapOf(

        // ─── Chat 1: Juan Carlos López — Plomería ───────────────
        1 to listOf(
            Mensaje("Hola, tengo una fuga de agua en el baño, ¿me puedes ayudar?",
                "10:30 a.m.", esUsuario = true),
            Mensaje("¡Hola! Claro que sí, con gusto. Puedo ayudarte. " +
                "¿Me puedes enviar una foto por favor?",
                "10:31 a.m.", esUsuario = false),
            Mensaje("[foto adjunta]", "10:32 a.m.", esUsuario = true,
                imagenUrl = "foto_fuga"),
            Mensaje("Listo, ya vi el problema. Mi precio normal es $60.000, " +
                "pero puedo dejarlo en $50.000 para ti.",
                "10:33 a.m.", esUsuario = false),
            Mensaje("¿Me lo puedes dejar en $45.000? Sí es hoy.",
                "10:34 a.m.", esUsuario = true),
            Mensaje("Te lo dejo en $45.000. ¡Allí te veo!",
                "10:34 a.m.", esUsuario = false),
        ),

        // ─── Chat 2: Miguel Ángel T. — Electricidad ─────────────
        2 to listOf(
            Mensaje("Buenos días, necesito revisar el tablero eléctrico, " +
                "se me fue la luz en dos habitaciones.",
                "9:00 a.m.", esUsuario = true),
            Mensaje("Buenos días. Perfecto, puedo ir esta tarde. " +
                "¿A qué hora le queda bien?",
                "9:05 a.m.", esUsuario = false),
            Mensaje("A las 3 pm está bien.", "9:06 a.m.", esUsuario = true),
            Mensaje("Listo, allá estaré. El presupuesto aproximado " +
                "is de $55.000 según lo que describes.",
                "9:07 a.m.", esUsuario = false),
            Mensaje("Perfecto, hasta las 3.", "9:08 a.m.", esUsuario = true),
            Mensaje("Servicio finalizado. ¡Hasta pronto!",
                "4:35 p.m.", esUsuario = false),
        ),

        // ─── Chat 3: Roberto Sánchez — Carpintería ───────────────
        3 to listOf(
            Mensaje("Hola Roberto, necesito arreglar una puerta que " +
                "no cierra bien.", "Lun 2:00 p.m.", esUsuario = true),
            Mensaje("Hola, con gusto lo reviso. ¿Puedo ir el miércoles?",
                "Lun 2:10 p.m.", esUsuario = false),
            Mensaje("Sí, el miércoles perfecto.",
                "Lun 2:12 p.m.", esUsuario = true),
            Mensaje("¿Cuándo puedo ir a revisar el trabajo?",
                "Lun 4:00 p.m.", esUsuario = false),
        ),

        // ─── Chat 4: Andrés Gómez — Cerrajería ───────────────────
        4 to listOf(
            Mensaje("Necesito cambiar la cerradura de la puerta principal.",
                "12 Abr 11:00 a.m.", esUsuario = true),
            Mensaje("Claro, ¿qué tipo de cerradura tiene actualmente?",
                "12 Abr 11:05 a.m.", esUsuario = false),
            Mensaje("Es una cerradura estándar de palanca.",
                "12 Abr 11:06 a.m.", esUsuario = true),
            Mensaje("Ok, el cambio quedaría en $80.000 incluyendo la " +
                "cerradura nueva.",
                "12 Abr 11:10 a.m.", esUsuario = false),
            Mensaje("Está un poco costoso, lo voy a pensar.",
                "12 Abr 11:12 a.m.", esUsuario = true),
            Mensaje("Entendido, cancelamos el servicio.",
                "12 Abr 11:13 a.m.", esUsuario = false),
        ),
    )
}
