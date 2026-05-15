package com.servicios.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.text.NumberFormat
import java.util.Locale

// ══════════════════════════════════════════════════════════════
// TarjetaServicioPopular.kt — CORREGIDO según Imagen 6
//
// La Imagen 6 muestra el diseño REAL de las tarjetas:
//   ┌──────────────┐
//   │   FOTO REAL  │  ← foto real con bordes redondeados
//   │   del servicio│     (sin overlay/scrim oscuro)
//   └──────────────┘
//   Nombre          ← texto blanco bold DEBAJO de la foto
//   Desde $110.000  ← precio en gris claro DEBAJO del nombre
//
// DIFERENCIA vs versión anterior:
//   ❌ Anterior: texto sobre la foto con overlay oscuro
//   ✅ Correcto: foto limpia + texto debajo (igual a Imagen 6)
//
// Precios correctos (Imagen 6):
//   Plomería:     $110.000
//   Electricidad: $55.000
//   Aseo:         $30.000
// ══════════════════════════════════════════════════════════════

@Composable
fun TarjetaServicioPopular(
    nombre   : String,
    precio   : Int,
    imageUrl : String?,
    onClick  : () -> Unit,
    modifier : Modifier = Modifier
) {
    val precioFormateado = NumberFormat
        .getNumberInstance(Locale.forLanguageTag("es-CO"))
        .format(precio)

    Column(
        modifier = modifier
            .width(150.dp)
            .clickable(onClick = onClick)
    ) {
        // ── Foto del servicio (bordes redondeados, sin overlay) ──
        // Imagen 6: fotos LIMPIAS sin degradado oscuro encima
        AsyncImage(
            model              = imageUrl,
            contentDescription = nombre,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ── Nombre del servicio (debajo, texto blanco bold) ──────
        Text(
            text       = nombre,
            color      = Color.White,
            fontSize   = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines   = 1
        )

        Spacer(modifier = Modifier.height(2.dp))

        // ── Precio (debajo, texto gris claro, italic-like) ───────
        // Imagen 6: "Desde $110.000" en color gris claro
        Text(
            text     = "Desde \$$precioFormateado",
            color    = Color(0xFFB0C4DE),
            fontSize = 11.sp,
            maxLines = 1
        )
    }
}
