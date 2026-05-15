package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.components.BarraBusqueda
import com.servicios.app.ui.components.BottomNavBar
import com.servicios.app.ui.components.CategoriaIcono
import com.servicios.app.ui.components.TarjetaEmergencia
import com.servicios.app.ui.components.TarjetaServicioPopular
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.viewmodel.HomeViewModel

// ══════════════════════════════════════════════════════════════
// HomeScreen.kt — VERSION FINAL CORREGIDA
//
// ✅ Usa iconoRes (drawable XML personalizado) en CategoriaIcono
// ✅ Grid de exactamente 8 ítems (sin ítem extra de "Ver más")
// ✅ TarjetaEmergencia con sirena fotográfica (Coil + fallback SVG)
// ✅ TarjetaServicioPopular con foto limpia + texto debajo (Imagen 6)
// ✅ Precios actualizados: Plomería $110K, Electricidad $55K, Aseo $30K
// ✅ BottomNavBar con fondo BLANCO
// ══════════════════════════════════════════════════════════════

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel    : HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = AzulFondo,
        bottomBar      = { BottomNavBar(navController = navController) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AzulFondo)
                .verticalScroll(rememberScrollState())
        ) {

            // ══════════════════════════════════════════════════
            // CABECERA: ciudad + campana + saludo + búsqueda
            // ══════════════════════════════════════════════════
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AzulPrimario)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                // Fila superior: ubicación + notificaciones
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier          = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector        = Icons.Filled.LocationOn,
                        contentDescription = "Ciudad",
                        tint               = Color.White,
                        modifier           = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text       = state.ciudad,
                        color      = Color.White,
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector        = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint               = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* TODO: Notificaciones */ }) {
                        Icon(
                            imageVector        = Icons.Filled.Notifications,
                            contentDescription = "Notificaciones",
                            tint               = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Saludo personalizado
                Text(
                    text     = "Hola, ${state.userName}",
                    color    = Color.White.copy(alpha = 0.85f),
                    fontSize = 15.sp
                )

                // Pregunta principal (AQUÍ LO CLAVE: salto de línea explícito)
                Text(
                    text       = "¿Qué servicio\nnecesitas hoy?",
                    color      = Color.White,
                    fontSize   = 26.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Barra de búsqueda
                BarraBusqueda(
                    valor         = state.busqueda,
                    onValorCambia = viewModel::onBusquedaCambia,
                    onBuscar      = viewModel::onBuscar
                )
            }

            // ══════════════════════════════════════════════════
            // GRID DE CATEGORÍAS — 4 columnas × 2 filas = 8 ítems
            // ══════════════════════════════════════════════════
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)   // Aumentado de 260.dp a 280.dp para los íconos de 80dp
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.categoriasGrid) { servicio ->
                    CategoriaIcono(
                        servicio = servicio,
                        onClick = {
                            if (servicio.esVerMas) {
                                navController.navigate(Ruta.TodasCategorias.path)
                            } else {
                                navController.navigate(
                                    Ruta.ListaServicios.conCategoria(servicio.id)
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ══════════════════════════════════════════════════
            // BANNER DE EMERGENCIA — Imagen 5
            // Sirena fotográfica cargada desde URL con Coil
            // ══════════════════════════════════════════════════
            TarjetaEmergencia(
                onSolicitarClick = { navController.navigate(Ruta.Emergencia.path) },
                modifier         = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            // ══════════════════════════════════════════════════
            // SERVICIOS POPULARES — Imagen 6
            // Fotos limpias + nombre + precio DEBAJO de la foto
            // ══════════════════════════════════════════════════

            // Encabezado de sección
            Row(
                modifier              = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text(
                    text       = "Servicios populares",
                    color      = Color.White,
                    fontSize   = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { /* TODO: ver todos */ }) {
                    Text(
                        text     = "Ver todos",
                        color    = Color(0xFF4F8EF7),    // azul claro (Imagen 6)
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Carrusel horizontal con FOTOS REALES y texto debajo
            LazyRow(
                contentPadding        = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier              = Modifier.padding(bottom = 20.dp)
            ) {
                items(state.serviciosPopulares) { servicio ->
                    TarjetaServicioPopular(
                        nombre   = servicio.nombre,
                        precio   = servicio.precio,
                        imageUrl = servicio.imageUrl,
                        onClick  = {
                            navController.navigate(
                                Ruta.DetalleServicio.conId(servicio.id)
                            )
                        }
                    )
                }
            }
        }
    }
}
