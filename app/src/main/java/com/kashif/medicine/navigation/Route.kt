package com.kashif.medicine.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Home

@Serializable
data class MedicineDetails(
    val id: String,
    val name: String,
    val dose: String,
    val strength: String
)