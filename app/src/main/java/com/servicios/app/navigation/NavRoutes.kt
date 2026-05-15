package com.servicios.app.navigation

sealed class Ruta(val path: String) {
    // Auth
    object Splash : Ruta("splash")
    object Onboarding : Ruta("onboarding")
    object Login        : Ruta("login")

    // Legacy: antiguo registro
    object Register     : Ruta("register")

    // NUEVO: flujo de registro moderno
    object RegistroTipo    : Ruta("registro_tipo")
    object RegistroUsuario : Ruta("registro_usuario")
    object RegistroPro     : Ruta("registro_pro")

    // Main Hub
    object Home : Ruta("home")
    object MisServicios : Ruta("misservicios")
    object Mensajes : Ruta("mensajes")
    object Perfil : Ruta("perfil")

    // Discovery & Booking
    object TodasCategorias : Ruta("todas_categorias")
    object ListaServicios : Ruta("lista_servicios/{categoria}") {
        fun conCategoria(categoria: String) = "lista_servicios/$categoria"
    }
    object Emergencia : Ruta("emergencia")
    object DetalleServicio : Ruta("detalle_servicio/{id}") {
        fun conId(id: String) = "detalle_servicio/$id"
    }
    object PerfilProfesional : Ruta("perfil_profesional/{id}") {
        fun conId(id: String) = "perfil_profesional/$id"
    }
    object Chat : Ruta("chat/{id}") {
        fun conId(id: String) = "chat/$id"
    }
    object ConfirmarServicio : Ruta("confirmar_servicio")
    object PagoSeguro : Ruta("pago_seguro")

    // Execution
    object ServicioEnProgreso : Ruta("servicio_en_progreso")
    object Calificacion : Ruta("calificacion")

    // Profile Actions
    object MetodosPago : Ruta("metodos_pago")
    object Direcciones : Ruta("direcciones_guardadas")
    object AyudaSoporte : Ruta("ayuda_soporte")
    object Configuracion : Ruta("configuracion")
    object RecargaSaldo : Ruta("recarga_saldo")
    object Disputa : Ruta("disputa")

    // Professional Flow
    object ProMain : Ruta("pro_main")
    object ValidacionPro : Ruta("validacion_pro")
}
