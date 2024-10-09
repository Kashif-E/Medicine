package com.kashif.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashif.core.domain.model.Medicine


@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract val medicineDao: MedicineDao
}