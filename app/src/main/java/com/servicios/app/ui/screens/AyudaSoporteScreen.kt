package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyudaSoporteScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ayuda y Soporte", color = BlancoPuro) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = BlancoPuro)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        },
        containerColor = AzulFondo
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Text("¿En qué podemos ayudarte?", color = BlancoPuro, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            SupportItem(
                icon = Icons.AutoMirrored.Filled.Help,
                title = "Preguntas Frecuentes",
                subtitle = "Encuentra respuestas rápidas",
                onClick = { /* TODO */ }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SupportItem(
                icon = Icons.AutoMirrored.Filled.Chat,
                title = "Chat de Soporte",
                subtitle = "Habla con un agente",
                onClick = { /* TODO */ }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SupportItem(
                icon = Icons.Filled.Email,
                title = "Enviar un Correo",
                subtitle = "Soporte@deuna.app",
                onClick = { /* TODO */ }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Seguridad", color = BlancoPuro, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Ruta.Disputa.path) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF7F1D1D).copy(alpha = 0.2f))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.ReportProblem, null, tint = Color(0xFFEF4444))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Reportar un problema / Disputa", color = BlancoPuro, fontWeight = FontWeight.Bold)
                        Text("Si tienes problemas con un servicio", color = TextoGris, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun SupportItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Color(0xFF3B82F6))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = BlancoPuro, fontWeight = FontWeight.Bold)
                Text(subtitle, color = TextoGris, fontSize = 12.sp)
            }
        }
    }
}
