package com.servicios.app.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.TextoSuave

@Composable
fun BottomNavBar(navController: NavController) {

    data class NavItem(val label: String, val icon: ImageVector, val ruta: Ruta)

    val items = listOf(
        NavItem("Inicio", Icons.Filled.Home, Ruta.Home),
        NavItem("Mis servicios", Icons.Filled.Receipt, Ruta.MisServicios),
        NavItem("Mensajes", Icons.AutoMirrored.Filled.Chat, Ruta.Mensajes),
        NavItem("Perfil", Icons.Filled.Person, Ruta.Perfil)
    )

    val backStack by navController.currentBackStackEntryAsState()
    val rutaActual = backStack?.destination?.route

    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp
        ) {
            items.forEach { item ->
                val seleccionado = rutaActual == item.ruta.path
                NavigationBarItem(
                    selected = seleccionado,
                    onClick = {
                        if (!seleccionado) {
                            navController.navigate(item.ruta.path) {
                                popUpTo(Ruta.Home.path) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(item.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AzulBoton,
                        selectedTextColor = AzulBoton,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = TextoSuave,
                        unselectedTextColor = TextoSuave
                    )
                )
            }
        }
    }
}
