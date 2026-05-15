package com.servicios.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.servicios.app.R
import com.servicios.app.ui.theme.AzulAccion
import com.servicios.app.ui.theme.AzulEmergencia
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

// ══════════════════════════════════════════════════════════════
// TarjetaEmergencia.kt — CORREGIDO y FIEL a la Imagen 5
//
// La Imagen 5 muestra:
//   • Fondo: tarjeta azul oscuro con degradado horizontal
//   • Izquierda: "¿Emergencia?" (bold blanco, grande)
//              + "Te ayudamos rápido" (gris claro)
//              + Botón "Solicitar ahora →" (azul brillante, redondeado)
//   • Derecha:  FOTO REAL de sirena azul de policía (photorealistic)
//
// Estrategia para la sirena:
//   1. AsyncImage carga la foto desde URL (con Coil) → más fiel a la imagen
//   2. Si no hay internet → usa ic_emergencia_sirena.xml como fallback
//   3. Para producción: añadir res/drawable/img_sirena.png (600x600px)
// ══════════════════════════════════════════════════════════════

// URL de sirena azul realista (similar a la Imagen 5)
private const val SIRENA_URL =
    "https://images.unsplash.com/photo-1614252235316-8c857d38b5f4?w=300&q=80"

@Composable
fun TarjetaEmergencia(
    onSolicitarClick: () -> Unit,
    modifier        : Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.horizontalGradient(
                    // Degradado de izquierda (más oscuro) a derecha (más brillante)
                    // Fiel al gradiente visible en la Imagen 5
                    colors    = listOf(
                        Color(0xFF0B1A3E),   // azul muy oscuro (izquierda)
                        Color(0xFF0E2154),   // azul medio
                        Color(0xFF1338A0)    // azul más brillante (derecha)
                    ),
                    startX    = 0f,
                    endX      = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier          = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 0.dp)
        ) {
            // ── Columna izquierda: texto + botón ─────────────
            Column(modifier = Modifier.weight(1f)) {

                // "¿Emergencia?" — título bold, blanco, grande
                Text(
                    text       = "¿Emergencia?",
                    color      = BlancoPuro,
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )

                Spacer(Modifier.height(4.dp))

                // "Te ayudamos rápido" — subtítulo gris
                Text(
                    text     = "Te ayudamos rápido",
                    color    = TextoGris,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(14.dp))

                // Botón "Solicitar ahora →" — azul brillante redondeado
                // Fiel a la Imagen 5: botón filled azul con texto blanco
                Button(
                    onClick = onSolicitarClick,
                    colors  = ButtonDefaults.buttonColors(
                        containerColor = AzulAccion,    // azul brillante #3A82FF
                        contentColor   = BlancoPuro
                    ),
                    shape   = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(44.dp)
                ) {
                    Text(
                        text       = "Solicitar ahora",
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector        = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }

            // ── Columna derecha: SIRENA FOTOGRÁFICA ──────────
            // Imagen 5 muestra sirena azul policial realista a la derecha
            Box(
                modifier         = Modifier
                    .size(130.dp)
                    .padding(end = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                // Intentar cargar foto real de sirena (Coil)
                // Si falla → muestra el vector drawable como fallback
                AsyncImage(
                    model              = SIRENA_URL,
                    contentDescription = "Sirena de emergencia",
                    contentScale       = ContentScale.Fit,
                    placeholder        = painterResource(R.drawable.ic_emergencia_sirena),
                    error              = painterResource(R.drawable.ic_emergencia_sirena),
                    modifier           = Modifier.size(120.dp)
                )
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════
// INSTRUCCIONES PARA PIXEL-PERFECT CON IMAGEN REAL:
//
// 1. Descarga una foto de sirena azul con fondo transparente (.png)
//    Sugerencia: busca "police siren png transparent background"
//    en sitios como Freepik, Vecteezy o genera con IA.
//
// 2. Renombra el archivo a: img_sirena.png
//    Dimensiones recomendadas: 600×600 px, fondo transparente
//
// 3. Colócala en: app/src/main/res/drawable/img_sirena.png
//
// 4. Reemplaza AsyncImage por:
//    Image(
//        painter = painterResource(R.drawable.img_sirena),
//        contentDescription = "Sirena",
//        contentScale = ContentScale.Fit,
//        modifier = Modifier.size(120.dp)
//    )
// ══════════════════════════════════════════════════════════════
