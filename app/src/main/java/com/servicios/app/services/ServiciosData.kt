package com.servicios.app.services

object ServiciosData {

    val enProgreso = listOf(
        ServicioHistorial(
            id = 1,
            profesional = "Juan Carlos López",
            especialidad = "Plomería",
            descripcion = "Fuga de agua en el baño",
            fecha = "Hoy, 11:30 a.m.",
            precio = "$45.000",
            calificacion = 0f,     // aún no calificado
            estado = EstadoServicio.EN_PROGRESO,
            direccion = "Calle 123 #45-67, Bogotá",
        ),
    )

    val historial = listOf(
        ServicioHistorial(
            id = 2,
            profesional = "Miguel Ángel T.",
            especialidad = "Electricidad",
            descripcion = "Revisión tablero eléctrico",
            fecha = "12 Abr 2024",
            precio = "$55.000",
            calificacion = 5.0f,
            estado = EstadoServicio.COMPLETADO,
            direccion = "Carrera 7 #80-20, Bogotá",
        ),
        ServicioHistorial(
            id = 3,
            profesional = "Roberto Sánchez",
            especialidad = "Cerrajería",
            descripcion = "Cambio de chapa puerta principal",
            fecha = "5 Abr 2024",
            precio = "$40.000",
            calificacion = 4.8f,
            estado = EstadoServicio.COMPLETADO,
            direccion = "Av. El Dorado #68-50, Bogotá",
        ),
        ServicioHistorial(
            id = 4,
            profesional = "Andrés Gómez",
            especialidad = "Pintura",
            descripcion = "Pintura sala y comedor",
            fecha = "18 Mar 2024",
            precio = "$120.000",
            calificacion = 4.5f,
            estado = EstadoServicio.COMPLETADO,
            direccion = "Cra 15 #93-40, Bogotá",
        ),
        ServicioHistorial(
            id = 5,
            profesional = "Carlos Martínez",
            especialidad = "Aseo",
            descripcion = "Limpieza profunda apartamento",
            fecha = "2 Mar 2024",
            precio = "$80.000",
            calificacion = 5.0f,
            estado = EstadoServicio.COMPLETADO,
            direccion = "Cl 116 #15-20, Bogotá",
        ),
    )

    val cancelados = listOf(
        ServicioHistorial(
            id = 6,
            profesional = "Pedro Rojas",
            especialidad = "Gas",
            descripcion = "Revisión de instalación de gas",
            fecha = "28 Feb 2024",
            precio = "$0",       // cancelado antes de pagar
            calificacion = 0f,
            estado = EstadoServicio.CANCELADO,
            direccion = "Cra 50 #26-20, Bogotá",
        ),
        ServicioHistorial(
            id = 7,
            profesional = "Luisa Fernanda C.",
            especialidad = "Carpintería",
            descripcion = "Reparación puerta balcón",
            fecha = "10 Ene 2024",
            precio = "$0",
            calificacion = 0f,
            estado = EstadoServicio.CANCELADO,
            direccion = "Av. Américas #36-15, Bogotá",
        ),
    )
}
