package com.servicios.app.ui.screens

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.servicios.app.databinding.FragmentMensajesBinding
import com.servicios.app.messages.ConversacionesAdapter
import com.servicios.app.messages.ConversacionesData
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import com.servicios.app.messages.ChatDetailActivity
import com.servicios.app.ui.components.BottomNavBar
import com.servicios.app.ui.theme.AzulFondo
import com.servicios.app.ui.theme.AzulPrimario

@Composable
fun MensajesScreen(navController: NavController) {
    Scaffold(
        containerColor = AzulFondo,
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(AzulFondo)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AzulPrimario)
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Mensajes",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            AndroidView(
                factory = { context ->
                    val binding = FragmentMensajesBinding.inflate(LayoutInflater.from(context))
                    
                    val adapter = ConversacionesAdapter(ConversacionesData.conversaciones) { conv ->
                        val intent = Intent(context, ChatDetailActivity::class.java).apply {
                            putExtra("conv_id",  conv.id)
                            putExtra("pro_name", conv.proNombre)
                            putExtra("pro_esp",  conv.proEspecialidad)
                        }
                        context.startActivity(intent)
                    }

                    binding.rvConversaciones.layoutManager = LinearLayoutManager(context)
                    binding.rvConversaciones.adapter = adapter
                    
                    binding.root
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
