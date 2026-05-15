package com.servicios.app

import com.servicios.app.viewmodel.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

// ─────────────────────────────────────────────────────────────
// HomeViewModelTest.kt
// Pruebas unitarias para HomeViewModel.
// No requieren emulador ni dispositivo físico.
// Ejecutar con: ./gradlew test
// ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        // HomeViewModel no tiene dependencias externas en su constructor,
        // por lo que puede instanciarse directamente en pruebas.
        viewModel = HomeViewModel()
    }

    // ── Prueba 1: nombre de usuario inicial ───────────────────
    @Test
    fun `estado inicial contiene nombre de usuario Laura`() = runTest {
        val estado = viewModel.uiState.first()
        assertEquals("Laura", estado.userName)
    }

    // ── Prueba 2: ciudad inicial ──────────────────────────────
    @Test
    fun `estado inicial contiene ciudad Bogota Colombia`() = runTest {
        val estado = viewModel.uiState.first()
        assertEquals("Bogotá, Colombia", estado.ciudad)
    }

    // ── Prueba 3: grid tiene exactamente 8 categorías ─────────
    @Test
    fun `categoriasGrid tiene exactamente 8 elementos`() = runTest {
        val estado = viewModel.uiState.first()
        // 7 servicios reales + 1 "Ver más"
        assertEquals(8, estado.categoriasGrid.size)
    }

    // ── Prueba 4: el último ítem es "Ver más" ─────────────────
    @Test
    fun `el ultimo elemento del grid es Ver mas`() = runTest {
        val estado = viewModel.uiState.first()
        val ultimo = estado.categoriasGrid.last()
        assertTrue(ultimo.esVerMas)
        assertEquals("Ver más", ultimo.nombre)
    }

    // ── Prueba 5: servicios populares son exactamente 3 ───────
    @Test
    fun `serviciosPopulares tiene exactamente 3 elementos`() = runTest {
        val estado = viewModel.uiState.first()
        assertEquals(3, estado.serviciosPopulares.size)
    }

    // ── Prueba 6: búsqueda vacía al inicio ───────────────────
    @Test
    fun `busqueda esta vacia al inicio`() = runTest {
        val estado = viewModel.uiState.first()
        assertTrue(estado.busqueda.isEmpty())
    }

    // ── Prueba 7: onBusquedaCambia actualiza el texto ─────────
    @Test
    fun `onBusquedaCambia actualiza el texto de busqueda`() = runTest {
        viewModel.onBusquedaCambia("Plomería")
        val estado = viewModel.uiState.first()
        assertEquals("Plomería", estado.busqueda)
    }

    // ── Prueba 8: "Ver más" no está en servicios populares ────
    @Test
    fun `serviciosPopulares no incluye el boton Ver mas`() = runTest {
        val estado = viewModel.uiState.first()
        val tieneVerMas = estado.serviciosPopulares.any { it.esVerMas }
        assertFalse(tieneVerMas)
    }

    // ── Prueba 9: todos los servicios tienen ID no vacío ──────
    @Test
    fun `todas las categorias tienen id no vacio`() = runTest {
        val estado = viewModel.uiState.first()
        estado.categoriasGrid.forEach { servicio ->
            assertTrue(
                "Servicio '${servicio.nombre}' tiene ID vacío",
                servicio.id.isNotBlank()
            )
        }
    }

    // ── Prueba 10: los servicios populares tienen imageUrl ────
    @Test
    fun `serviciosPopulares tienen imageUrl no vacia`() = runTest {
        val estado = viewModel.uiState.first()
        estado.serviciosPopulares.forEach { servicio ->
            assertTrue(
                "Servicio '${servicio.nombre}' no tiene imageUrl",
                servicio.imageUrl?.isNotBlank() == true
            )
        }
    }
}
