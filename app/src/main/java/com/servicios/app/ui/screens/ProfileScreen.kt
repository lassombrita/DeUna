package com.servicios.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.servicios.app.auth.SessionManager
import com.servicios.app.navigation.Ruta
import com.servicios.app.ui.components.BottomNavBar
import com.servicios.app.ui.theme.*
import com.servicios.app.viewmodel.ProfileViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel    : ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = BlancoPuro,
            dragHandle = { BottomSheetDefaults.DragHandle(color = TextoSuave) }
        ) {
            ProfileSettingsContent(
                sessions = state.sessions,
                currentSession = state.currentSession,
                onSessionClick = { 
                    viewModel.switchSession(it)
                    showSheet = false
                },
                onConfigClick = {
                    showSheet = false
                    navController.navigate(Ruta.Configuracion.path)
                },
                onLogoutClick = {
                    showSheet = false
                    viewModel.logout()
                    // Podrías navegar a Login aquí
                }
            )
        }
    }

    Scaffold(
        containerColor = FondoClaro,
        bottomBar      = { BottomNavBar(navController = navController) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(FondoClaro)
                .verticalScroll(rememberScrollState())
        ) {

            // ══════════════════════════════════════════════════
            // SECCIÓN SUPERIOR AZUL
            // ══════════════════════════════════════════════════
            Surface(
                color  = AzulPrimario,
                shape  = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    CabeceraUsuario(
                        userName        = state.userName,
                        email           = state.email,
                        avatarUrl       = state.avatarUrl,
                        onSettingsClick = { showSheet = true }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TarjetaSaldo(
                        balance         = state.balance,
                        onRechargeClick = { navController.navigate(Ruta.RecargaSaldo.path) }
                    )
                }
            }
            // ... resto del código ...

            // ══════════════════════════════════════════════════
            // SECCIÓN BLANCA: acciones rápidas + menú
            // ══════════════════════════════════════════════════
            Surface(
                color    = BlancoCard,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)   // pequeño gap visual
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 22.dp)
                ) {
                    // Título de la sección
                    Text(
                        text       = "Mis servicios",
                        color      = TextoOscuro,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Fila de 4 acciones rápidas
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AccionRapida(
                            icon    = Icons.Filled.Schedule,
                            label   = "En progreso",
                            onClick = { navController.navigate(Ruta.ServicioEnProgreso.path) }
                        )
                        AccionRapida(
                            icon    = Icons.Filled.History,
                            label   = "Historial",
                            onClick = { /* TODO */ }
                        )
                        AccionRapida(
                            icon    = Icons.Filled.FavoriteBorder,
                            label   = "Favoritos",
                            onClick = { /* TODO */ }
                        )
                        AccionRapida(
                            icon    = Icons.Outlined.Cancel,
                            label   = "Cancelados",
                            onClick = { /* TODO */ }
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))
                    HorizontalDivider(color = DividerColor, thickness = 1.dp)

                    // ── Opciones de menú ─────────────────────
                    OpcionMenu(
                        icon    = Icons.Filled.CreditCard,
                        title   = "Métodos de pago",
                        onClick = { navController.navigate(Ruta.MetodosPago.path) }
                    )
                    HorizontalDivider(color = DividerColor.copy(alpha = 0.5f), thickness = 0.5.dp)

                    OpcionMenu(
                        icon    = Icons.Filled.LocationOn,
                        title   = "Direcciones guardadas",
                        onClick = { navController.navigate(Ruta.Direcciones.path) }
                    )
                    HorizontalDivider(color = DividerColor.copy(alpha = 0.5f), thickness = 0.5.dp)

                    OpcionMenu(
                        icon    = Icons.AutoMirrored.Filled.HelpOutline,
                        title   = "Ayuda y soporte",
                        onClick = { navController.navigate(Ruta.AyudaSoporte.path) }
                    )
                    HorizontalDivider(color = DividerColor.copy(alpha = 0.5f), thickness = 0.5.dp)

                    OpcionMenu(
                        icon    = Icons.Filled.Settings,
                        title   = "Configuración",
                        onClick = { navController.navigate(Ruta.Configuracion.path) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = {
                            viewModel.logout()
                            navController.navigate(Ruta.Login.path) {
                                popUpTo(Ruta.Splash.path) { inclusive = true }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

/** Contenido del BottomSheet de Configuración */
@Composable
private fun ProfileSettingsContent(
    sessions: List<SessionManager.Sesion>,
    currentSession: SessionManager.Sesion?,
    onSessionClick: (SessionManager.Sesion) -> Unit,
    onConfigClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = "Configuración de cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextoOscuro,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )

        Text(
            text = "Cambiar de cuenta",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TextoMedio,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
        )

        sessions.forEach { session ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSessionClick(session) }
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF0F2F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = session.nombre.take(1).uppercase(),
                        color = AzulPrimario,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = session.nombre, color = TextoOscuro, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = session.email, color = TextoGris, fontSize = 13.sp)
                }
                if (session.id == currentSession?.id) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Seleccionada",
                        tint = Color(0xFF4ADE80),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 16.dp))

        OpcionMenu(
            icon = androidx.compose.material.icons.Icons.Default.Settings,
            title = "Configuración avanzada",
            onClick = onConfigClick
        )

        TextButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text("Cerrar Sesión", color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Sub-composables privados
// ─────────────────────────────────────────────────────────────

/** Cabecera del perfil: foto circular + nombre + email + engranaje */
@Composable
private fun CabeceraUsuario(
    userName       : String,
    email          : String,
    avatarUrl      : String?,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier          = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ── Avatar circular ───────────────────────────────────
        Box(
            modifier         = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(Color(0xFFD0D8E8)),
            contentAlignment = Alignment.Center
        ) {
            if (avatarUrl != null) {
                // Carga foto real desde URL (Coil)
                AsyncImage(
                    model              = avatarUrl,
                    contentDescription = "Foto de $userName",
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier.fillMaxSize()
                )
            } else {
                // Inicial del nombre como fallback
                // TODO: cuando tengas la imagen real, colocarla en
                //       res/drawable/img_avatar_default.png (58×58 dp)
                Text(
                    text       = userName.take(1).uppercase(),
                    color      = AzulPrimario,
                    fontSize   = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        // ── Nombre y email ────────────────────────────────────
        Column(modifier = Modifier.weight(1f)) {
            // "Hola, Laura 👋"  (emoji incluido)
            Text(
                text       = "Hola, $userName \uD83D\uDC4B",
                color      = BlancoPuro,
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis
            )
            Text(
                text     = email,
                color    = TextoGris,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // ── Engranaje de configuración ────────────────────────
        IconButton(onClick = onSettingsClick) {
            Icon(
                imageVector        = Icons.Filled.Settings,
                contentDescription = "Configuración",
                tint               = BlancoPuro,
                modifier           = Modifier.size(24.dp)
            )
        }
    }
}

/** Tarjeta azul de saldo en cuenta + botón Recargar */
@Composable
private fun TarjetaSaldo(
    balance        : Int,
    onRechargeClick: () -> Unit
) {
    val saldoFormateado = NumberFormat
        .getNumberInstance(Locale.forLanguageTag("es-CO"))
        .format(balance)

    Surface(
        color    = AzulBoton,
        shape    = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text     = "Saldo en cuenta",
                    color    = BlancoPuro.copy(alpha = 0.85f),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text       = "\$$saldoFormateado",
                    color      = BlancoPuro,
                    fontSize   = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Botón Recargar
            Button(
                onClick = onRechargeClick,
                colors  = ButtonDefaults.buttonColors(
                    containerColor = AzulAccion,
                    contentColor   = BlancoPuro
                ),
                shape   = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Recargar", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

/** Acción rápida circular: ícono en círculo gris claro + label */
@Composable
private fun AccionRapida(
    icon   : ImageVector,
    label  : String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier            = Modifier
            .width(74.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier         = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F2F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector        = icon,
                contentDescription = label,
                tint               = Color(0xFF374151),
                modifier           = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text      = label,
            color     = TextoMedio,
            fontSize  = 11.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

/** Fila de opción de menú: ícono + título + chevron derecho */
@Composable
private fun OpcionMenu(
    icon   : ImageVector,
    title  : String,
    onClick: () -> Unit
) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = icon,
            contentDescription = title,
            tint               = TextoMedio,
            modifier           = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text     = title,
            color    = TextoOscuro,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector        = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Ir a $title",
            tint               = TextoSuave,
            modifier           = Modifier.size(15.dp)
        )
    }
}
