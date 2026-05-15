package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoCard
import com.servicios.app.ui.theme.TextoMedio
import com.servicios.app.ui.theme.TextoOscuro

@Composable
fun RegisterTypeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulPrimario)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear cuenta",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Elige el tipo de cuenta con la que quieres registrarte.",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Ruta.RegistroUsuario.path) },
            colors = CardDefaults.cardColors(containerColor = BlancoCard),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Usuario", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = TextoOscuro)
                Spacer(Modifier.height(6.dp))
                Text(
                    "Para personas que buscan contratar servicios para su hogar u oficina.",
                    color = TextoMedio,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Ruta.RegistroPro.path) },
            colors = CardDefaults.cardColors(containerColor = BlancoCard),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Profesional", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = TextoOscuro)
                Spacer(Modifier.height(6.dp))
                Text(
                    "Para expertos que ofrecen servicios y quieren recibir más clientes.",
                    color = TextoMedio,
                    fontSize = 13.sp
                )
            }
        }
    }
}
