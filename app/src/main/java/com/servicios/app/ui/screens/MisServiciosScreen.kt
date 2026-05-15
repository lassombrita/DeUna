package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.components.BottomNavBar
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario

// FIX: Eliminado AndroidView + FragmentMisServiciosBinding (XML legacy)
// Reemplazado por TabRow + LazyColumn 100% Compose
@Composable
fun MisServiciosScreen(navController: NavController) {
    var tabSeleccionado by remember { mutableIntStateOf(0) }
    val tabs = listOf("En Progreso", "Historial", "Cancelados", "Publicaciones")

    Scaffold(
        containerColor = AzulFondo,
        bottomBar      = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AzulFondo)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AzulPrimario)
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text       = "Mis Servicios",
                    color      = Color.White,
                    fontSize   = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // FIX: TabRow nativo de Compose (reemplaza TabLayout XML)
            ScrollableTabRow(
                selectedTabIndex  = tabSeleccionado,
                containerColor    = AzulPrimario,
                contentColor      = Color.White,
                edgePadding       = 0.dp
            ) {
                tabs.forEachIndexed { index, titulo ->
                    Tab(
                        selected = tabSeleccionado == index,
                        onClick  = { tabSeleccionado = index },
                        text     = { Text(titulo, fontSize = 13.sp) }
                    )
                }
            }

            // Contenido por tab
            when (tabSeleccionado) {
                0 -> TabEnProgreso(navController)
                1 -> TabPlaceholder("No tienes servicios en historial")
                2 -> TabPlaceholder("No tienes servicios cancelados")
                3 -> TabPlaceholder("No tienes publicaciones aún")
            }
        }
    }
}

@Composable
private fun TabEnProgreso(navController: NavController) {
    LazyColumn(
        modifier            = Modifier.fillMaxSize(),
        contentPadding      = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors   = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Plomería - Juan Carlos López", fontWeight = FontWeight.Bold)
                    Text("En progreso · $50.000", color = Color.Gray, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { navController.navigate(Ruta.ServicioEnProgreso.path) },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Ver progreso") }
                }
            }
        }
    }
}

@Composable
private fun TabPlaceholder(mensaje: String) {
    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(mensaje, color = Color.Gray)
    }
}
