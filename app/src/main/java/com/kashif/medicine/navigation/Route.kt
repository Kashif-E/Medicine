package com.kashif.medicine.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Home(val email: String = "")

@Serializable
data class MedicineDetails(
    val id: String,
    val name: String,
    val dose: String,
    val strength: String
)