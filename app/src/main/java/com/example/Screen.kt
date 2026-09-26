package com.example

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object SimpleCalculator : Screen("simple_calculator")
    object AdvancedCalculator : Screen("advanced_calculator")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Login : Screen("login")
    object SignUp : Screen("sign_up")
}
