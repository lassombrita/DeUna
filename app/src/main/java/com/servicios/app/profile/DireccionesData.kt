package com.servicios.app.profile

object DireccionesData {

    val direcciones = listOf(

        // Dirección actual (coincide con el header de HomeFragment)
        Direccion(
            id = 1,
            alias = "Casa",
            direccionCompleta = "Calle 123 #45-67, Apto 302",
            barrio = "Chapinero Alto",
            ciudad = "Bogotá, Colombia",
            esActual = true,
            icono = "🏠",
        ),

        Direccion(
            id = 2,
            alias = "Trabajo",
            direccionCompleta = "Carrera 7 #71-21, Of. 504",
            barrio = "Quinta Camacho",
            ciudad = "Bogotá, Colombia",
            icono = "🏠",
        ),

        Direccion(
            id = 3,
            alias = "Mamá",
            direccionCompleta = "Cra 50 #26-20, Casa 5",
            barrio = "Galerías",
            ciudad = "Bogotá, Colombia",
            icono = "🏠",
        ),

        Direccion(
            id = 4,
            alias = "Apartamento Medellín",
            direccionCompleta = "Av. El Poblado #5A-30, Apto 1201",
            barrio = "El Poblado",
            ciudad = "Medellín, Colombia",
            icono = "🏠",
        ),

        Direccion(
            id = 5,
            alias = "Finca",
            direccionCompleta = "Km 8 vía Calarcá, finca Las Palmas",
            barrio = "Zona Rural",
            ciudad = "Armenia, Colombia",
            icono = "🏠",
        ),
    )
}
