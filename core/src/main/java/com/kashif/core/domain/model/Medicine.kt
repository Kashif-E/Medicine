package com.kashif.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dose: String,
    val strength: String
)


fun MedicineResponse.asDomainModel(): List<Medicine> {
    return problems.flatMap { it.asDomainModel() }
}

fun Problem.asDomainModel(): List<Medicine> {
    val diabetesMedicines = diabetes.orEmpty().flatMap { it.asDomainModel() }
    return diabetesMedicines
}

fun Diabetes.asDomainModel(): List<Medicine> {
    return medications.orEmpty().flatMap { it.asDomainModel() }
}

fun Medication.asDomainModel(): List<Medicine> {
    return medicationsClasses.orEmpty().flatMap { it.asDomainModel() }
}

fun MedicationsClass.asDomainModel(): List<Medicine> {
    val classNameMedicines = className.orEmpty().flatMap { it.asDomainModel() }
    val className2Medicines = className2.orEmpty().flatMap { it.asDomainModel() }
    return classNameMedicines + className2Medicines
}

fun ClassName.asDomainModel(): List<Medicine> {
    val associatedDrugMedicines = associatedDrug.orEmpty().map { it.asDomainModel() }
    val associatedDrug2Medicines = associatedDrug2.orEmpty().map { it.asDomainModel() }
    return associatedDrugMedicines + associatedDrug2Medicines
}

fun AssociatedDrug.asDomainModel(): Medicine {
    return Medicine(
        name = name,
        dose = dose,
        strength = strength
    )
}
