package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
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
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceListScreen(navController: NavController, categoria: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoria.replaceFirstChar { it.uppercase() }, color = BlancoPuro) },
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
        // Simulación de lista de profesionales
        val profesionales = listOf(
            Profesional("1", "Juan Pérez", "Plomero experto", 4.8, 45000, "https://images.unsplash.com/photo-1540560085022-b8d28e263d6b?q=80&w=200&h=200&auto=format&fit=crop"),
            Profesional("2", "María García", "Electricista certificada", 4.9, 55000, "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=200&h=200&auto=format&fit=crop"),
            Profesional("3", "Carlos López", "Mantenimiento general", 4.5, 30000, "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=200&h=200&auto=format&fit=crop")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(profesionales) { pro ->
                ProfesionalCard(pro) {
                    navController.navigate(Ruta.PerfilProfesional.conId(pro.id))
                }
            }
        }
    }
}

data class Profesional(
    val id: String,
    val nombre: String,
    val especialidad: String,
    val calificacion: Double,
    val tarifaBase: Int,
    val imageUrl: String
)

@Composable
fun ProfesionalCard(pro: Profesional, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pro.imageUrl,
                contentDescription = pro.nombre,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(pro.nombre, color = BlancoPuro, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(pro.especialidad, color = TextoGris, fontSize = 13.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFB300), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(pro.calificacion.toString(), color = BlancoPuro, fontSize = 12.sp)
                }
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text("Desde", color = TextoGris, fontSize = 10.sp)
                Text("\$${pro.tarifaBase}", color = Color(0xFF4ADE80), fontWeight = FontWeight.Bold)
            }
        }
    }
}
