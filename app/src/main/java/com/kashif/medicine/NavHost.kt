package com.kashif.medicine

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kashif.feature_login.ui.LoginScreen

@Composable
fun MedicineNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                onNavigate = { navController.navigate(Home) }
            )
        }


    }
}