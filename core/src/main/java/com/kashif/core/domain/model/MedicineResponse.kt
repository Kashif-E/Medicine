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
    val medications: List<Medication>? = null,
    val labs: List<Lab>? = null
)

@Serializable
data class Medication(
    val medicationsClasses: List<MedicationsClass>? = null
)

@Serializable
data class MedicationsClass(
    val className: List<ClassName>? = null,
    val className2: List<ClassName>? = null
)

@Serializable
data class ClassName(
    val associatedDrug: List<AssociatedDrug>? = null,

    @SerialName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug>? = null
)

@Serializable
data class AssociatedDrug(
    val name: String,
    val dose: String,
    val strength: String
)

@Serializable
data class Lab(
    @SerialName("missing_field")
    val missingField: String
)