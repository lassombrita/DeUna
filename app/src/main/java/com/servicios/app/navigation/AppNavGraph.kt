package com.servicios.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.servicios.app.ui.screens.AyudaSoporteScreen
import com.servicios.app.ui.screens.ChatScreen
import com.servicios.app.ui.screens.ConfirmarServicioScreen
import com.servicios.app.ui.screens.DetalleServicioScreen
import com.servicios.app.ui.screens.DisputeScreen
import com.servicios.app.ui.screens.EmergenciaScreen
import com.servicios.app.ui.screens.HomeScreen
import com.servicios.app.ui.screens.LoginScreen
import com.servicios.app.ui.screens.OnboardingScreen
import com.servicios.app.ui.screens.PagoSeguroScreen
import com.servicios.app.ui.screens.PlaceholderScreen
import com.servicios.app.ui.screens.ProMainScreen
import com.servicios.app.ui.screens.ProProfileScreen
import com.servicios.app.ui.screens.ProfileScreen
import com.servicios.app.ui.screens.RatingScreen
import com.servicios.app.ui.screens.RecargaSaldoScreen
import com.servicios.app.ui.screens.RegisterScreen
import com.servicios.app.ui.screens.ServicioEnProgresoScreen
import com.servicios.app.ui.screens.ServiceListScreen
import com.servicios.app.ui.screens.MensajesScreen
import com.servicios.app.ui.screens.MisServiciosScreen
import com.servicios.app.ui.screens.SettingsBridgeScreen
import com.servicios.app.ui.screens.AddressesBridgeScreen
import com.servicios.app.ui.screens.PaymentsBridgeScreen
import com.servicios.app.ui.screens.SplashScreen
import com.servicios.app.ui.screens.ValidationScreen
import com.servicios.app.ui.screens.RegisterTypeScreen
import com.servicios.app.ui.screens.RegisterUserScreen
import com.servicios.app.ui.screens.RegisterProScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Ruta.Splash.path
    ) {
        // --- Auth ---
        composable(Ruta.Splash.path) {
            SplashScreen(navController = navController)
        }
        composable(Ruta.Onboarding.path) {
            OnboardingScreen(navController = navController)
        }
        composable(Ruta.Login.path) {
            LoginScreen(navController = navController)
        }
        composable(Ruta.Register.path) {
            RegisterScreen(navController = navController)
        }

        // NUEVO flujo de registro
        composable(Ruta.RegistroTipo.path) {
            RegisterTypeScreen(navController = navController)
        }
        composable(Ruta.RegistroUsuario.path) {
            RegisterUserScreen(navController = navController)
        }
        composable(Ruta.RegistroPro.path) {
            RegisterProScreen(navController = navController)
        }

        // --- Main ---
        composable(Ruta.Home.path) {
            HomeScreen(navController = navController)
        }
        composable(Ruta.MisServicios.path) {
            MisServiciosScreen(navController = navController)
        }
        composable(Ruta.Mensajes.path) {
            MensajesScreen(navController = navController)
        }
        composable(Ruta.Perfil.path) {
            ProfileScreen(navController = navController)
        }

        // --- Discovery & Booking ---
        composable(Ruta.TodasCategorias.path) {
            PlaceholderScreen("Todas las categorías")
        }
        composable(
            route = Ruta.ListaServicios.path,
            arguments = listOf(navArgument("categoria") { type = NavType.StringType })
        ) { backStack ->
            val cat = backStack.arguments?.getString("categoria") ?: ""
            ServiceListScreen(navController = navController, categoria = cat)
        }
        composable(Ruta.Emergencia.path) {
            EmergenciaScreen(navController = navController)
        }
        composable(
            route = Ruta.DetalleServicio.path,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            DetalleServicioScreen(navController = navController, id = id)
        }
        composable(
            route = Ruta.PerfilProfesional.path,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            ProProfileScreen(navController = navController, id = id)
        }
        composable(
            route = Ruta.Chat.path,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            ChatScreen(navController = navController, id = id)
        }
        composable(Ruta.ConfirmarServicio.path) {
            ConfirmarServicioScreen(navController = navController)
        }
        composable(Ruta.PagoSeguro.path) {
            PagoSeguroScreen(navController = navController)
        }

        // --- Execution ---
        composable(Ruta.ServicioEnProgreso.path) {
            ServicioEnProgresoScreen(navController = navController)
        }
        composable(Ruta.Calificacion.path) {
            RatingScreen(navController = navController)
        }

        // --- Profile ---
        composable(Ruta.MetodosPago.path) {
            PaymentsBridgeScreen(navController = navController)
        }
        composable(Ruta.Direcciones.path) {
            AddressesBridgeScreen(navController = navController)
        }
        composable(Ruta.AyudaSoporte.path) {
            AyudaSoporteScreen(navController = navController)
        }
        composable(Ruta.Configuracion.path) {
            SettingsBridgeScreen(navController = navController)
        }
        composable(Ruta.RecargaSaldo.path) {
            RecargaSaldoScreen(navController = navController)
        }
        composable(Ruta.Disputa.path) {
            DisputeScreen(navController = navController)
        }

        // --- Professional ---
        composable(Ruta.ProMain.path) {
            ProMainScreen(navController = navController)
        }
        composable(Ruta.ValidacionPro.path) {
            ValidationScreen(navController = navController)
        }
    }
}
