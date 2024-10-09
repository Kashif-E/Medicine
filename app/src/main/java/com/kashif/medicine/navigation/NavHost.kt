package com.kashif.medicine.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kashif.core.domain.model.Medicine
import com.kashif.feature_home.ui.HomeScreen
import com.kashif.feature_home.ui.MedicineDetailScreen
import com.kashif.feature_login.ui.LoginScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun MedicineNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen { email ->
                navController.navigate(Home(email))
            }
        }

        composable<Home> { backStackEntry ->
            val email = backStackEntry.toRoute<Home>().email
            HomeScreen(email = email, onNavigate = { medicine ->
                navController.navigate(
                    MedicineDetails(
                        id = medicine.id,
                        name = medicine.name,
                        dose = medicine.dose,
                        strength = medicine.strength
                    )
                )
            })
        }

        composable<MedicineDetails>(typeMap = mapOf(typeOf<MedicineDetails>() to CustomNavType.medicineDetailsType)) { backStackEntry ->
            val medicine = backStackEntry.toRoute<MedicineDetails>()
            MedicineDetailScreen(
                Medicine(
                    id = medicine.id,
                    name = medicine.name,
                    dose = medicine.dose,
                    strength = medicine.strength
                )
            ) {
                navController.popBackStack()
            }
        }

    }
}

object CustomNavType {
    val medicineDetailsType = object : NavType<MedicineDetails>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): MedicineDetails {
            return Json.decodeFromString(bundle.getString(key) ?: "")
        }

        override fun parseValue(value: String): MedicineDetails {
            return Json.decodeFromString(
                Uri.decode(value)
            )
        }

        override fun put(bundle: Bundle, key: String, value: MedicineDetails) {
            bundle.putSerializable(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value: MedicineDetails): String {
            return Uri.encode(Json.encodeToString(value))
        }


    }
}
