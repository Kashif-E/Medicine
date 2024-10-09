package com.kashif.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.model.User


@Database(entities = [Medicine::class, User::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract val medicineDao: MedicineDao
    abstract val userDao: UserDao
}