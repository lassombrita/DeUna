package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterProScreen(navController: NavController) {
    // Paso actual del formulario (0, 1, 2)
    var paso by remember { mutableIntStateOf(0) }

    // Datos básicos
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Perfil profesional
    val profesiones = listOf("Plomería", "Electricidad", "Cerrajería", "Aseo", "Carpintería", "Pintura")
    val experiencias = listOf("0-1 año", "1-3 años", "3-5 años", "5+ años")
    var profesion by remember { mutableStateOf(profesiones.first()) }
    var experiencia by remember { mutableStateOf(experiencias[2]) }
    var descripcion by remember { mutableStateOf("") }
    var tieneCertificados by remember { mutableStateOf(false) }

    // Detalle y contacto
    var ciudad by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var disponibilidad by remember { mutableStateOf("Entre semana") }
    val disponibilidades = listOf("Entre semana", "Fines de semana", "Horario completo")

    var expProfesion by remember { mutableStateOf(false) }
    var expExperiencia by remember { mutableStateOf(false) }
    var expDisponibilidad by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = FondoClaro,
        topBar = {
            TopAppBar(
                title = { Text("Registro de profesional", color = BlancoPuro) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Indicador de pasos
            StepIndicator(pasoActual = paso)

            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                when (paso) {
                    0 -> {
                        Text("Datos básicos", fontWeight = FontWeight.SemiBold, color = TextoOscuro)
                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre completo") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(8.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Correo electrónico") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Contraseña") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    1 -> {
                        Text("Perfil profesional", fontWeight = FontWeight.SemiBold, color = TextoOscuro)
                        Spacer(Modifier.height(12.dp))

                        // Profesión
                        ExposedDropdownMenuBox(
                            expanded = expProfesion,
                            onExpandedChange = { expProfesion = !expProfesion }
                        ) {
                            OutlinedTextField(
                                value = profesion,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Profesión u oficio") },
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expProfesion,
                                onDismissRequest = { expProfesion = false }
                            ) {
                                profesiones.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            profesion = item
                                            expProfesion = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        // Años de experiencia
                        ExposedDropdownMenuBox(
                            expanded = expExperiencia,
                            onExpandedChange = { expExperiencia = !expExperiencia }
                        ) {
                            OutlinedTextField(
                                value = experiencia,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Años de experiencia") },
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expExperiencia,
                                onDismissRequest = { expExperiencia = false }
                            ) {
                                experiencias.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            experiencia = item
                                            expExperiencia = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Descripción breve", color = TextoMedio, fontSize = 13.sp)
                        OutlinedTextField(
                            value = descripcion,
                            onValueChange = { if (it.length <= 300) descripcion = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 100.dp),
                            placeholder = { Text("Cuéntale a tus clientes cómo trabajas y qué te hace diferente") }
                        )

                        Spacer(Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = tieneCertificados, onCheckedChange = { tieneCertificados = it })
                            Spacer(Modifier.width(8.dp))
                            Text("Cuento con títulos/certificaciones", color = TextoOscuro)
                        }
                    }

                    2 -> {
                        Text("Detalle y contacto", fontWeight = FontWeight.SemiBold, color = TextoOscuro)
                        Spacer(Modifier.height(12.dp))

                        // Disponibilidad
                        ExposedDropdownMenuBox(
                            expanded = expDisponibilidad,
                            onExpandedChange = { expDisponibilidad = !expDisponibilidad }
                        ) {
                            OutlinedTextField(
                                value = disponibilidad,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Disponibilidad") },
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expDisponibilidad,
                                onDismissRequest = { expDisponibilidad = false }
                            ) {
                                disponibilidades.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            disponibilidad = item
                                            expDisponibilidad = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = ciudad,
                            onValueChange = { ciudad = it },
                            label = { Text("Ciudad / zona de atención") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(8.dp))

                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Teléfono / WhatsApp") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Botones inferior: Atrás / Siguiente o Crear cuenta
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (paso > 0) {
                    TextButton(onClick = { paso-- }) {
                        Text("Atrás")
                    }
                } else {
                    Spacer(Modifier.width(8.dp))
                }

                Button(
                    onClick = {
                        if (paso < 2) {
                            paso++
                        } else {
                            // TODO: lógica real de registro profesional
                            navController.navigate(Ruta.ProMain.path) {
                                popUpTo(Ruta.Splash.path) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulBoton)
                ) {
                    Text(
                        text = if (paso < 2) "Siguiente" else "Crear cuenta profesional",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun StepIndicator(pasoActual: Int) {
    val pasos = listOf(
        "Datos" to "Básicos",
        "Perfil" to "Profesional",
        "Detalle" to "y contacto"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        pasos.forEachIndexed { index, (titulo, subtitulo) ->
            val activo = index == pasoActual
            val circleColor = if (activo) AzulBoton else TextoSuave
            val borderColor = if (activo) AzulBoton else TextoSuave.copy(alpha = 0.4f)
            val textColor = if (activo) TextoOscuro else TextoSuave

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Círculo con número de paso
                Box(
                    modifier = Modifier
                        .size(if (activo) 26.dp else 22.dp)
                        .background(color = BlancoCard, shape = androidx.compose.foundation.shape.CircleShape)
                        .border(
                            width = 2.dp,
                            color = borderColor,
                            shape = androidx.compose.foundation.shape.CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (index + 1).toString(),
                        color = circleColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if (activo) 14.sp else 12.sp
                    )
                }

                Spacer(Modifier.width(8.dp))

                // Etiquetas del paso
                Column {
                    Text(
                        text = titulo,
                        color = textColor,
                        fontSize = 12.sp,
                        fontWeight = if (activo) FontWeight.SemiBold else FontWeight.Medium
                    )
                    Text(
                        text = subtitulo,
                        color = textColor.copy(alpha = 0.8f),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
