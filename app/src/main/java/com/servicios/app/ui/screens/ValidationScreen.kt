package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.TextoGris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Validación Profesional", color = BlancoPuro) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = BlancoPuro)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        },
        containerColor = AzulFondo
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Text(
                "Niveles de Validación",
                color = BlancoPuro,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Aumenta tu confianza y visibilidad validando tu identidad y experiencia.",
                color = TextoGris,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            ValidationItem(
                title = "Nivel 1: Identidad Básica",
                description = "Validación de correo electrónico y número de celular.",
                isCompleted = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            ValidationItem(
                title = "Nivel 2: Documentación",
                description = "Carga de documento de identidad y antecedentes judiciales.",
                isCompleted = false,
                onActionClick = { /* Cargar docs */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ValidationItem(
                title = "Nivel 3: Certificación Técnica",
                description = "Entrevista técnica o carga de diplomas y certificaciones.",
                isCompleted = false,
                onActionClick = { /* Agendar */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Info, null, tint = Color(0xFF3B82F6))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Los profesionales de Nivel 3 tienen prioridad en la asignación de servicios premium.",
                        color = TextoGris,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ValidationItem(
    title: String,
    description: String,
    isCompleted: Boolean,
    onActionClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = if (isCompleted) Color(0xFF4ADE80) else TextoGris,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = BlancoPuro, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(description, color = TextoGris, fontSize = 13.sp)
            }

            if (!isCompleted && onActionClick != null) {
                TextButton(onClick = onActionClick) {
                    Text("VALIDAR", color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
