package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

@Composable
fun ServicioEnProgresoScreen(navController: NavController) {
    Scaffold(
        containerColor = AzulFondo,
        bottomBar = {
            Button(
                onClick = { navController.navigate(Ruta.Calificacion.path) },
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ADE80)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Finalizar Servicio", color = AzulPrimario, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Mapa simulado (Placeholder)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xFF334155)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.LocationOn, null, tint = Color.Red, modifier = Modifier.size(48.dp))
                    Text("GPS / Mapa en tiempo real", color = BlancoPuro)
                }
            }
            
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Servicio en curso", color = BlancoPuro, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("El profesional está en camino / trabajando", color = TextoGris, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1540560085022-b8d28e263d6b?q=80&w=200&h=200&auto=format&fit=crop",
                            contentDescription = null,
                            modifier = Modifier.size(56.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Juan Pérez", color = BlancoPuro, fontWeight = FontWeight.Bold)
                            Text("Plomero", color = TextoGris, fontSize = 13.sp)
                        }
                        
                        IconButton(onClick = { /* Llamar */ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF334155))) {
                            Icon(Icons.Filled.Call, null, tint = BlancoPuro)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { navController.navigate(Ruta.Chat.conId("1")) }, colors = IconButtonDefaults.iconButtonColors(containerColor = AzulBoton)) {
                            Icon(Icons.AutoMirrored.Filled.Chat, null, tint = BlancoPuro)
                        }
                    }
                }
            }
        }
    }
}
