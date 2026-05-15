package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
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
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

@Composable
fun RatingScreen(navController: NavController) {
    var rating by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "¿Cómo fue tu servicio?",
                color = BlancoPuro,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                "Tu calificación ayuda a mejorar la comunidad.",
                color = TextoGris,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Estrellas
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarOutline,
                        contentDescription = null,
                        tint = if (i <= rating) Color(0xFFFFB300) else TextoGris,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { rating = i }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            OutlinedTextField(
                value = comentario,
                onValueChange = { comentario = it },
                placeholder = { Text("Escribe un comentario (opcional)", color = TextoGris) },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = BlancoPuro,
                    unfocusedTextColor = BlancoPuro,
                    focusedBorderColor = AzulBoton,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f)
                )
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = {
                    navController.navigate(Ruta.Home.path) {
                        popUpTo(Ruta.Home.path) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AzulBoton),
                shape = RoundedCornerShape(12.dp),
                enabled = rating > 0
            ) {
                Text("Enviar Calificación", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(onClick = {
                navController.navigate(Ruta.Home.path) {
                    popUpTo(Ruta.Home.path) { inclusive = true }
                }
            }) {
                Text("Omitir", color = TextoGris)
            }
        }
    }
}
