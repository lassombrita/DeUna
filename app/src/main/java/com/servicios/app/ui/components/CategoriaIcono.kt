package com.servicios.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.servicios.app.model.Servicio
import com.servicios.app.ui.theme.AzulBoton

@Composable
fun CategoriaIcono(
    servicio: Servicio,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(80.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(if (servicio.iconRes == 0) AzulBoton else Color.Transparent)
        ) {
            if (servicio.iconRes != 0) {
                // Forzamos un ID válido si por alguna razón servicio.iconRes llegara a ser 0
                val validIconId = if (servicio.iconRes != 0) servicio.iconRes else com.servicios.app.R.drawable.ic_star_empty
                Image(
                    painter = painterResource(id = validIconId),
                    contentDescription = servicio.nombre,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(22.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = servicio.nombre,
            color = Color.White,
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
