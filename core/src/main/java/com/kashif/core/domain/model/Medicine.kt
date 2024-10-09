package com.kashif.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(
    @PrimaryKey
    val id: String,
    val name: String,
    val dose: String,
    val strength: String
)

fun MedicineResponse.asDomainModel(): List<Medicine> {
    return problems.flatMap { it.asDomainModel() }
}

fun Problem.asDomainModel(): List<Medicine> {
    val diabetesMedicines = diabetes.orEmpty().flatMap { it.asDomainModel() }
    val asthmaMedicines = asthma.orEmpty().flatMap { it.asDomainModel() }
    return diabetesMedicines + asthmaMedicines
}

fun Diabetes.asDomainModel(): List<Medicine> {
    return medications.orEmpty().flatMap { it.asDomainModel() }
}

fun Asthma.asDomainModel(): List<Medicine> {
    return medications.orEmpty().flatMap { it.asDomainModel() }
}

fun Medication.asDomainModel(): List<Medicine> {
    return medicationsClasses.orEmpty().flatMap { it.asDomainModel() }
}

fun MedicationsClass.asDomainModel(): List<Medicine> {
    return data.orEmpty().flatMap { it.asDomainModel() }
}

fun DataItem.asDomainModel(): List<Medicine> {
    return associatedDrug.orEmpty().map { it.asDomainModel() }
}

fun AssociatedDrug.asDomainModel(): Medicine {
    return Medicine(
        id = id,
        name = name,
        dose = dose,
        strength = strength
    )
}

