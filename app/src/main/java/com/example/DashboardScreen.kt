package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome back 👋", style = MaterialTheme.typography.headlineMedium)
        Text("Calculate your business profit in seconds.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 16.dp))

        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), onClick = { navController.navigate(Screen.SimpleCalculator.route) }) {
            Text("Simple Calculator", modifier = Modifier.padding(16.dp))
        }
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), onClick = { navController.navigate(Screen.AdvancedCalculator.route) }) {
            Text("Advanced Calculator", modifier = Modifier.padding(16.dp))
        }
    }
}
