package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulPrimario)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.White.copy(alpha = 0.7f)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = BlancoPuro,
                unfocusedTextColor = BlancoPuro,
                focusedBorderColor = AzulBoton,
                unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = Color.White.copy(alpha = 0.7f)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = BlancoPuro,
                unfocusedTextColor = BlancoPuro,
                focusedBorderColor = AzulBoton,
                unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
            )
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { 
                // Simulación de login exitoso
                navController.navigate(Ruta.Home.path) {
                    popUpTo(Ruta.Splash.path) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)
        ) {
            Text("Entrar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = { navController.navigate(Ruta.RegistroTipo.path) }) {
            Text("¿No tienes cuenta? Regístrate", color = Color.White)
        }
    }
}
