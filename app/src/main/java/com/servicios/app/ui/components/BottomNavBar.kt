package com.servicios.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.servicios.app.navigation.Ruta

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

    NavigationBar(
        containerColor = Color(0xFF0D2E6E)
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
                    Icon(item.icon, contentDescription = item.label)
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1A56C4),
                    selectedTextColor = Color(0xFF1A56C4),
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color(0xFFB0C4DE),
                    unselectedTextColor = Color(0xFFB0C4DE)
                )
            )
        }
    }
}
