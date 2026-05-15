package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulPrimario

import androidx.compose.ui.tooling.preview.Preview
import com.servicios.app.ui.theme.ServiciosAppTheme

@Composable
fun OnboardingScreen(navController: NavController) {
    OnboardingContent(
        onStartClick = { navController.navigate(Ruta.Register.path) },
        onLoginClick = { navController.navigate(Ruta.Login.path) }
    )
}

@Composable
fun OnboardingContent(
    onStartClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulPrimario)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido a\nDe Una",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Encuentra a los mejores profesionales para el cuidado y mantenimiento de tus espacios en un solo lugar.",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)
        ) {
            Text("Comenzar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = onLoginClick) {
            Text("Ya tengo una cuenta", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ServiciosAppTheme {
        OnboardingContent({}, {})
    }
}
