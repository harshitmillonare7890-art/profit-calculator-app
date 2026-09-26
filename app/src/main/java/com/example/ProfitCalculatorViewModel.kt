package com.example

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CalculatorUiState(
    val productName: String = "",
    val productCost: String = "",
    val sellingPrice: String = "",
    val advertisingCost: String = "0",
    val shippingCost: String = "0",
    val platformFee: String = "0",
    val otherCosts: String = "0",
    val result: CalculationResult? = null
)

data class CalculationResult(
    val totalCost: Double,
    val netProfit: Double,
    val profitMargin: Double,
    val costPercentage: Double,
    val breakEvenPrice: Double,
    val status: String
)

class ProfitCalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun updateProductName(name: String) {
        _uiState.update { it.copy(productName = name) }
    }

    fun updateProductCost(cost: String) {
        _uiState.update { it.copy(productCost = cost) }
    }

    fun updateSellingPrice(price: String) {
        _uiState.update { it.copy(sellingPrice = price) }
    }

    fun updateAdvertisingCost(cost: String) {
        _uiState.update { it.copy(advertisingCost = cost) }
    }

    fun updateShippingCost(cost: String) {
        _uiState.update { it.copy(shippingCost = cost) }
    }

    fun updatePlatformFee(fee: String) {
        _uiState.update { it.copy(platformFee = fee) }
    }

    fun updateOtherCosts(cost: String) {
        _uiState.update { it.copy(otherCosts = cost) }
    }

    fun calculate() {
        val state = _uiState.value
        val productCost = state.productCost.toDoubleOrNull() ?: 0.0
        val sellingPrice = state.sellingPrice.toDoubleOrNull() ?: 0.0
        val advertisingCost = state.advertisingCost.toDoubleOrNull() ?: 0.0
        val shippingCost = state.shippingCost.toDoubleOrNull() ?: 0.0
        val platformFee = state.platformFee.toDoubleOrNull() ?: 0.0
        val otherCosts = state.otherCosts.toDoubleOrNull() ?: 0.0

        val totalCost = productCost + advertisingCost + shippingCost + platformFee + otherCosts
        val netProfit = sellingPrice - totalCost
        val profitMargin = if (sellingPrice != 0.0) (netProfit / sellingPrice) * 100 else 0.0
        val costPercentage = if (sellingPrice != 0.0) (totalCost / sellingPrice) * 100 else 0.0
        val breakEvenPrice = totalCost
        val status = when {
            netProfit > 0 -> "PROFITABLE"
            netProfit < 0 -> "LOSS"
            else -> "BREAK-EVEN"
        }

        _uiState.update {
            it.copy(
                result = CalculationResult(
                    totalCost, netProfit, profitMargin, costPercentage, breakEvenPrice, status
                )
            )
        }
    }
}
