package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.ProfitCalculatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ProfitCalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfitCalculatorTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val screens = listOf(
                    Screen.Dashboard,
                    Screen.SimpleCalculator,
                    Screen.AdvancedCalculator,
                    Screen.History,
                    Screen.Settings
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute != Screen.Login.route && currentRoute != Screen.SignUp.route) {
                            NavigationBar {
                                screens.forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                when (screen) {
                                                    Screen.Dashboard -> Icons.Default.Dashboard
                                                    Screen.SimpleCalculator -> Icons.Default.Calculate
                                                    Screen.AdvancedCalculator -> Icons.Default.Science
                                                    Screen.History -> Icons.Default.History
                                                    Screen.Settings -> Icons.Default.Settings
                                                    else -> Icons.Default.Home
                                                },
                                                contentDescription = screen.route
                                            )
                                        },
                                        selected = currentRoute == screen.route,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Login.route) { /* LoginScreen() */ }
                        composable(Screen.SignUp.route) { /* SignUpScreen() */ }
                        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
                        composable(Screen.SimpleCalculator.route) { CalculatorScreen(viewModel) }
                        composable(Screen.AdvancedCalculator.route) { /* AdvancedCalculatorScreen() */ }
                        composable(Screen.History.route) { /* HistoryScreen() */ }
                        composable(Screen.Settings.route) { /* SettingsScreen() */ }
                        composable(Screen.Profile.route) { /* ProfileScreen() */ }
                    }
                }
            }
        }
    }
}
