package com.servicios.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.FondoClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = FondoClaro,
        topBar = {
            TopAppBar(
                title = { Text("Registro de usuario", color = BlancoPuro) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crea tu cuenta para contratar servicios de forma segura.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = com.servicios.app.ui.theme.TextoOscuro
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    unfocusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = com.servicios.app.ui.theme.TextoMedio
                )
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    unfocusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = com.servicios.app.ui.theme.TextoMedio
                )
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    unfocusedTextColor = com.servicios.app.ui.theme.TextoOscuro,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = com.servicios.app.ui.theme.TextoMedio
                )
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    // TODO: lógica real de registro usuario
                    navController.navigate(Ruta.Home.path) {
                        popUpTo(Ruta.Splash.path) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)
            ) {
                Text("Crear cuenta", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
