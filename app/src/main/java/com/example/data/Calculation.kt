package com.example.data

data class Calculation(
    val id: String = "",
    val productName: String,
    val productCost: Double,
    val sellingPrice: Double,
    val advertisingCost: Double,
    val shippingCost: Double,
    val platformFee: Double,
    val otherCosts: Double,
    val tax: Double = 0.0,
    val quantity: Int = 1,
    val netProfit: Double,
    val profitMargin: Double,
    val roi: Double,
    val totalCost: Double,
    val revenue: Double,
    val createdAt: Long = System.currentTimeMillis()
)
