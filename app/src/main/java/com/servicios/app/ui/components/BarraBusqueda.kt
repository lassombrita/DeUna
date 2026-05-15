package com.servicios.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.servicios.app.ui.theme.AzulBoton

// ─────────────────────────────────────────────────────────────
// BarraBusqueda.kt
// Barra de búsqueda fiel a la imagen:
//   - Fondo blanco con bordes redondeados (pill shape)
//   - Ícono de lupa gris a la izquierda
//   - TextField transparente al centro
//   - Botón azul cuadrado-redondeado con lupa blanca a la derecha
// ─────────────────────────────────────────────────────────────

@Composable
fun BarraBusqueda(
    valor         : String,
    onValorCambia : (String) -> Unit,
    onBuscar      : () -> Unit,
    modifier      : Modifier = Modifier
) {
    Row(
        modifier           = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(Color.White, RoundedCornerShape(28.dp)),
        verticalAlignment  = Alignment.CenterVertically
    ) {
        // Lupa decorativa izquierda
        Icon(
            imageVector        = Icons.Filled.Search,
            contentDescription = null,
            tint               = Color(0xFFAAAAAA),
            modifier           = Modifier.padding(start = 14.dp)
        )

        // Campo de texto
        TextField(
            value             = valor,
            onValueChange     = onValorCambia,
            placeholder       = {
                Text("Buscar servicio...", color = Color(0xFFAAAAAA))
            },
            colors            = TextFieldDefaults.colors(
                focusedContainerColor   = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor             = AzulBoton
            ),
            keyboardOptions   = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions   = KeyboardActions(onSearch = { onBuscar() }),
            singleLine        = true,
            modifier          = Modifier.weight(1f)
        )

        // Botón de búsqueda azul (esquina derecha)
        IconButton(
            onClick  = onBuscar,
            modifier = Modifier
                .padding(end = 5.dp)
                .size(44.dp)
                .background(AzulBoton, RoundedCornerShape(22.dp))
        ) {
            Icon(
                imageVector        = Icons.Filled.Search,
                contentDescription = "Buscar",
                tint               = Color.White,
                modifier           = Modifier.size(22.dp)
            )
        }
    }
}
