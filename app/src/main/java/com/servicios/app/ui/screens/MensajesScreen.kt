package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.servicios.app.messages.Conversacion
import com.servicios.app.messages.ConversacionesData
import com.servicios.app.messages.EstadoConversacion
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.components.BottomNavBar
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.FondoClaro
import com.servicios.app.ui.theme.TextoMedio
import com.servicios.app.ui.theme.TextoOscuro
import com.servicios.app.ui.theme.TextoSuave

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MensajesScreen(navController: NavController) {
    Scaffold(
        containerColor = FondoClaro,
        topBar = {
            TopAppBar(
                title = { Text("Mensajes", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(FondoClaro)
        ) {
            if (ConversacionesData.conversaciones.isEmpty()) {
                // Estado vacío moderno
                EmptyMessagesState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(
                        items = ConversacionesData.conversaciones,
                        key = { it.id }
                    ) { conv ->
                        TarjetaConversacion(
                            conv = conv,
                            onClick = {
                                // Navega a la pantalla de chat usando tu NavGraph
                                navController.navigate(Ruta.Chat.conId(conv.id.toString()))
                            }
                        )
                        HorizontalDivider(
                            color = Color(0xFFE5E7EB),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyMessagesState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Aún no tienes mensajes",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextoOscuro
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Cuando empieces a contratar servicios, verás aquí tus conversaciones con los profesionales.",
            fontSize = 14.sp,
            color = TextoMedio
        )
    }
}

@Composable
private fun TarjetaConversacion(
    conv: Conversacion,
    onClick: () -> Unit
) {
    val colorEstado = when (conv.estado) {
        EstadoConversacion.EN_PROGRESO -> Color(0xFF2563EB)
        EstadoConversacion.COMPLETADO  -> Color(0xFF16A34A)
        EstadoConversacion.CANCELADO   -> Color(0xFFDC2626)
    }
    val textoEstado = when (conv.estado) {
        EstadoConversacion.EN_PROGRESO -> "En progreso"
        EstadoConversacion.COMPLETADO  -> "Completado"
        EstadoConversacion.CANCELADO   -> "Cancelado"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circular
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(AzulPrimario.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            if (conv.proAvatarUrl.isNotBlank()) {
                AsyncImage(
                    model = conv.proAvatarUrl,
                    contentDescription = conv.proNombre,
                    modifier = Modifier.fillMaxSize().clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = AzulPrimario,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            // Nombre + hora
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = conv.proNombre,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = TextoOscuro,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = conv.hora,
                    fontSize = 12.sp,
                    color = TextoSuave
                )
            }

            Spacer(Modifier.height(2.dp))

            // Especialidad + chip de estado
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = conv.proEspecialidad,
                    fontSize = 12.sp,
                    color = TextoMedio
                )
                Spacer(Modifier.width(6.dp))
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = colorEstado.copy(alpha = 0.08f)
                ) {
                    Text(
                        text = textoEstado,
                        fontSize = 10.sp,
                        color = colorEstado,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // Último mensaje + badge
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = conv.ultimoMensaje,
                    fontSize = 13.sp,
                    color = if (conv.mensajesNoLeidos > 0) TextoOscuro else TextoMedio,
                    fontWeight = if (conv.mensajesNoLeidos > 0) FontWeight.SemiBold else FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                if (conv.mensajesNoLeidos > 0) {
                    Spacer(Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(AzulBoton),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = conv.mensajesNoLeidos.toString(),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
