package com.servicios.app.ui.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.servicios.app.settings.ConfiguracionActivity

@Composable
fun SettingsBridgeScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(Intent(context, ConfiguracionActivity::class.java))
        navController.popBackStack()
    }
}

@Composable
fun AddressesBridgeScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(Intent(context, com.servicios.app.profile.AddressesActivity::class.java))
        navController.popBackStack()
    }
}

@Composable
fun PaymentsBridgeScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(Intent(context, com.servicios.app.payment.PaymentMethodsActivity::class.java))
        navController.popBackStack()
    }
}
