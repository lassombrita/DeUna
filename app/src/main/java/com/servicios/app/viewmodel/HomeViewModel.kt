package com.servicios.app.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import com.servicios.app.model.Servicio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.servicios.app.R
import javax.inject.Inject

data class HomeUiState(
    val userName: String = "Laura",
    val ciudad: String = "Bogotá, Colombia",
    val categoriasGrid: List<Servicio> = emptyList(),
    val serviciosPopulares: List<Servicio> = emptyList(),
    val busqueda: String = "",
    val esCargando: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        val categorias = listOf(
            Servicio(
                id = "plomeria",
                nombre = "Plomería",
                iconRes = R.drawable.ic_plomeria,   // JPG
                precio = 110_000,
                imageUrl = "https://images.unsplash.com/photo-1581244277943-fe4a9c777189?w=300&q=80"
            ),
            Servicio(
                id = "electricidad",
                nombre = "Electricidad",
                iconRes = R.drawable.ic_electricidad, // NUEVO .jpeg
                precio = 58_000,
                imageUrl = "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?w=300&q=80"
            ),
            Servicio(
                id = "cerrajeria",
                nombre = "Cerrajería",
                iconRes = R.drawable.ic_cerrajeria,   // NUEVO .jpeg
                precio = 90_000,
                imageUrl = "https://images.unsplash.com/photo-1517646281634-1aa8721470ee?w=300&q=80"
            ),
            Servicio(
                id = "aseo",
                nombre = "Aseo",
                iconRes = R.drawable.ic_star_empty, // Cambiado de 0 a star fallback
                precio = 40_000,
                imageUrl = "https://images.unsplash.com/photo-1581578731548-c64695ce6958?w=300&q=80"
            ),
            Servicio(
                id = "pintura",
                nombre = "Pintura",
                iconRes = R.drawable.ic_pintura,    // JPG
                precio = 80_000,
                imageUrl = "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?w=300&q=80"
            ),
            Servicio(
                id = "gas",
                nombre = "Gas",
                iconRes = R.drawable.ic_gas,        // JPG
                precio = 45_000,
                imageUrl = "https://images.unsplash.com/photo-1521207418485-99c705420785?w=300&q=80"
            ),
            Servicio(
                id = "carpinteria",
                nombre = "Carpintería",
                iconRes = R.drawable.ic_carpinteria, // JPG
                precio = 70_000,
                imageUrl = "https://images.unsplash.com/photo-1533090161767-e6ffed986c88?w=300&q=80"
            ),
            Servicio(
                id = "ver_mas",
                nombre = "Ver más",
                iconRes = R.drawable.ic_star_empty, // Cambiado de 0 a star fallback
                precio = 0,
                imageUrl = null,
                esVerMas = true
            )
        )

        _uiState.value = _uiState.value.copy(
            categoriasGrid = categorias,
            serviciosPopulares = categorias.take(3)
        )
    }

    fun onBusquedaCambia(texto: String) {
        _uiState.value = _uiState.value.copy(busqueda = texto)
    }

    fun onBuscar() {
        // Lógica de búsqueda implementada
        val query = _uiState.value.busqueda
        // TODO: Filtrar categorías o navegar a resultados
    }
}
