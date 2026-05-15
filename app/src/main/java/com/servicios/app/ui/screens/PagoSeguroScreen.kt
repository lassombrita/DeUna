package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris
import kotlinx.coroutines.delay

@Composable
fun PagoSeguroScreen(navController: NavController) {
    var pagoCompletado by remember { mutableStateOf(false) }

    // FIX: Eliminada navegación automática por LaunchedEffect

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            if (!pagoCompletado) {
                CircularProgressIndicator(color = AzulBoton, modifier = Modifier.size(64.dp))
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Procesando pago seguro...",
                    color = BlancoPuro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lock, null, tint = TextoGris, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Pago protegido por Escrow", color = TextoGris, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { pagoCompletado = true }) {
                    Text("Confirmar Pago Simulado")
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4ADE80)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Check, null, tint = BlancoPuro, modifier = Modifier.size(48.dp))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "¡Pago Exitoso!",
                    color = BlancoPuro,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "El dinero se mantendrá seguro hasta que confirmes la finalización del servicio.",
                    color = TextoGris,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = {
                        navController.navigate(Ruta.ServicioEnProgreso.path) {
                            popUpTo(Ruta.Home.path) { inclusive = false }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AzulBoton),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text("Ver mi servicio", color = BlancoPuro, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
