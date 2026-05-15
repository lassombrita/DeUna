package com.servicios.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.servicios.app.auth.SessionManager
import com.servicios.app.model.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Estado de la pantalla de Perfil. */
data class ProfileUiState(
    val userName  : String = "",
    val email     : String = "",
    val balance   : Int    = 0,
    val avatarUrl : String? = null,
    val sessions  : List<SessionManager.Sesion> = emptyList(),
    val currentSession: SessionManager.Sesion? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val walletRepository: WalletRepository
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeBalance()
        loadSessions()
    }

    private fun observeBalance() {
        viewModelScope.launch {
            walletRepository.balance.collectLatest { newBalance ->
                _uiState.value = _uiState.value.copy(balance = newBalance)
            }
        }
    }

    private fun loadSessions() {
        val sessions = SessionManager.getSesiones(getApplication())
        val current = SessionManager.getSesionActual(getApplication())
        _uiState.value = _uiState.value.copy(
            sessions = sessions,
            currentSession = current,
            userName = current?.nombre ?: "Usuario",
            email = current?.email ?: "",
            avatarUrl = current?.avatarUrl?.ifEmpty { null }
        )
    }

    fun switchSession(session: SessionManager.Sesion) {
        SessionManager.setSesionActual(getApplication(), session)
        loadSessions()
    }

    fun logout() {
        SessionManager.cerrarSesion(getApplication())
        _uiState.value = _uiState.value.copy(currentSession = null)
    }

    fun recharge(amount: Int) {
        viewModelScope.launch {
            walletRepository.recharge(amount)
        }
    }

    fun pay(amount: Int) {
        viewModelScope.launch {
            walletRepository.pay(amount)
        }
    }

    // Navegación (simulada o delegada al NavController en la UI)
    fun onRechargeClick()        { /* Acción manejada en el ProfileScreen */ }
    fun onInProgressClick()      { /* TODO */ }
    fun onHistoryClick()         { /* TODO */ }
    fun onFavoritesClick()       { /* TODO */ }
    fun onCancelledClick()       { /* TODO */ }
    fun onPaymentMethodsClick()  { /* TODO */ }
    fun onSavedAddressesClick()  { /* TODO */ }
    fun onHelpSupportClick()     { /* TODO */ }
    fun onSettingsClick()        { /* TODO */ }
}
