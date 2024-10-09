package com.kashif.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineResponse(
    val problems: List<Problem>
)

@Serializable
data class Problem(
    @SerialName("Diabetes")
    val diabetes: List<Diabetes>? = null,

    @SerialName("Asthma")
    val asthma: List<Asthma>? = null
)

@Serializable
data class Diabetes(
    val medications: List<Medication>? = null,
    val labs: List<Lab>? = null
)

@Serializable
data class Asthma(
    val medications: List<Medication>? = null
)

@Serializable
data class Medication(
    val medicationsClasses: List<MedicationsClass>? = null
)

@Serializable
data class MedicationsClass(
    val data: List<DataItem>? = null
)

@Serializable
data class DataItem(
    val associatedDrug: List<AssociatedDrug>? = null
)

@Serializable
data class AssociatedDrug(
    val id: String,
    val name: String,
    val dose: String,
    val strength: String
)

@Serializable
data class Lab(
    @SerialName("missing_field")
    val missingField: String
)
