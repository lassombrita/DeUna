package com.servicios.app.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface WalletRepository {
    val balance: StateFlow<Int>
    val paymentMethods: List<PaymentMethod>
    suspend fun recharge(amount: Int)
    suspend fun pay(amount: Int): Boolean
}

@Singleton
class InMemoryWalletRepository @Inject constructor() : WalletRepository {
    private val _balance = MutableStateFlow(120_000)
    override val balance: StateFlow<Int> = _balance.asStateFlow()

    override val paymentMethods: List<PaymentMethod> = listOf(
        PaymentMethod("1", "Nequi", null, PaymentType.NEQUI),
        PaymentMethod("2", "Daviplata", null, PaymentType.DAVIPLATA),
        PaymentMethod("3", "PSE", null, PaymentType.PSE)
    )

    override suspend fun recharge(amount: Int) {
        if (amount > 0) {
            _balance.value += amount
        }
    }

    override suspend fun pay(amount: Int): Boolean {
        if (amount <= 0) return false
        return if (_balance.value >= amount) {
            _balance.value -= amount
            true
        } else {
            false
        }
    }
}
