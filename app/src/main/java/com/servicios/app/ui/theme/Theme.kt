package com.servicios.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colores base de la guía
val AzulPrimario = Color(0xFF0D2E6E)
val AzulBoton = Color(0xFF1A56C4)
val AzulEmergencia = Color(0xFF172F6A)
val BlancoPuro = Color(0xFFFFFFFF)
val TextoGris = Color(0xFFB0C4DE)

// Colores adicionales requeridos por las pantallas existentes
val AzulFondo = Color(0xFF0D2E6E)      // Mismo que Primario para consistencia
val AzulAccion = Color(0xFF3A82FF)     // Azul brillante para botones de acción
val FondoClaro = Color(0xFFF3F4F6)     // Fondo gris muy claro para secciones de contenido
val BlancoCard = Color(0xFFFFFFFF)
val DividerColor = Color(0xFFE5E7EB)
val TextoOscuro = Color(0xFF1F2937)
val TextoMedio = Color(0xFF4B5563)
val TextoSuave = Color(0xFF9CA3AF)

private val ColorScheme = darkColorScheme(
    primary = AzulBoton,
    onPrimary = BlancoPuro,
    background = AzulPrimario,
    surface = AzulEmergencia,
    onBackground = BlancoPuro,
    onSurface = BlancoPuro,
    secondary = TextoGris,
)

@Composable
fun ServiciosAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        content = content
    )
}
