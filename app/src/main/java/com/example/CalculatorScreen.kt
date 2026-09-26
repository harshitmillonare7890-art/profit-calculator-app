package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CalculatorScreen(viewModel: ProfitCalculatorViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)) {
        Text("Small Business Profit Calculator", style = MaterialTheme.typography.headlineMedium)
        Text("Know your profit before you sell.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = uiState.productName,
            onValueChange = viewModel::updateProductName,
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.productCost,
            onValueChange = viewModel::updateProductCost,
            label = { Text("Product Cost (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.sellingPrice,
            onValueChange = viewModel::updateSellingPrice,
            label = { Text("Selling Price (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.advertisingCost,
            onValueChange = viewModel::updateAdvertisingCost,
            label = { Text("Advertising Cost (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.shippingCost,
            onValueChange = viewModel::updateShippingCost,
            label = { Text("Shipping Cost (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.platformFee,
            onValueChange = viewModel::updatePlatformFee,
            label = { Text("Platform Fee (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.otherCosts,
            onValueChange = viewModel::updateOtherCosts,
            label = { Text("Other Costs (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = viewModel::calculate, modifier = Modifier.fillMaxWidth()) {
            Text("Calculate Profit")
        }
        
        uiState.result?.let {
            Card(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("PROFIT ANALYSIS", style = MaterialTheme.typography.titleLarge)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Selling Price: ₹${uiState.sellingPrice}")
                    Text("Total Cost: ₹${it.totalCost}")
                    Text("NET PROFIT: ₹${it.netProfit}", style = MaterialTheme.typography.headlineMedium)
                    Text("Profit Margin: ${"%.2f".format(it.profitMargin)}%")
                    Text("Break-Even: ₹${it.breakEvenPrice}")
                    Text("Cost Percentage: ${"%.2f".format(it.costPercentage)}%")
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text(it.status, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
