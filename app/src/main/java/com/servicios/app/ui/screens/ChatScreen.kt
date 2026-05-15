package com.servicios.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.theme.AzulBoton
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario
import com.servicios.app.ui.theme.BlancoPuro
import com.servicios.app.ui.theme.ServiciosAppTheme
import com.servicios.app.ui.theme.TextoGris

@Composable
fun ChatScreen(navController: NavController, id: String) {
    ChatContent(
        onBackClick = { navController.popBackStack() },
        onAcceptPayClick = { navController.navigate(Ruta.ConfirmarServicio.path) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatContent(
    onBackClick: () -> Unit,
    onAcceptPayClick: () -> Unit
) {
    var mensaje by remember { mutableStateOf("") }
    
    // Simulación de mensajes
    val mensajes = listOf(
        ChatMessage("Hola Juan, necesito arreglar una fuga en el baño.", true),
        ChatMessage("Hola! Claro que sí. ¿Podrías enviarme una foto de la fuga?", false),
        ChatMessage("Aquí tienes. ¿Cuánto me cobrarías por el arreglo?", true),
        ChatMessage("Veo que es una tubería rota. Te cobraría \$45.000 por la mano de obra más materiales.", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Avatar simplificado
                        Surface(
                            modifier = Modifier.size(32.dp),
                            shape = RoundedCornerShape(16.dp),
                            color = Color.Gray
                        ) {}
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Juan Pérez", color = BlancoPuro, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("En línea", color = Color(0xFF4ADE80), fontSize = 12.sp)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = BlancoPuro)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulPrimario)
            )
        },
        containerColor = AzulFondo,
        bottomBar = {
            Surface(
                color = AzulPrimario,
                modifier = Modifier.fillMaxWidth().navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = mensaje,
                        onValueChange = { mensaje = it },
                        placeholder = { Text("Escribe un mensaje...", color = TextoGris) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = BlancoPuro,
                            unfocusedTextColor = BlancoPuro,
                            focusedContainerColor = Color(0xFF1E293B),
                            unfocusedContainerColor = Color(0xFF1E293B),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    IconButton(
                        onClick = { /* Enviar */ },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = AzulBoton)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, null, tint = BlancoPuro)
                    }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Banner de negociación de precio
            Surface(
                color = Color(0xFF334155),
                modifier = Modifier.fillMaxWidth().clickable(onClick = onAcceptPayClick)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Precio acordado: $45.000", color = Color(0xFF86EFAC), fontWeight = FontWeight.Bold)
                    Text("ACEPTAR Y PAGAR >", color = BlancoPuro, fontSize = 12.sp)
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                reverseLayout = false,
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mensajes) { msg ->
                    ChatBubble(msg)
                }
            }
        }
    }
}

data class ChatMessage(val text: String, val isMe: Boolean)

@Composable
fun ChatBubble(msg: ChatMessage) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (msg.isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            color = if (msg.isMe) AzulBoton else Color(0xFF1E293B),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (msg.isMe) 16.dp else 0.dp,
                bottomEnd = if (msg.isMe) 0.dp else 16.dp
            )
        ) {
            Text(
                text = msg.text,
                color = BlancoPuro,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun ChatPreview() {
    ServiciosAppTheme {
        ChatContent({}, {})
    }
}
