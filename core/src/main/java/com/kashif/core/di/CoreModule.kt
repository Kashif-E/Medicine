package com.kashif.core.di

import android.content.Context
import androidx.room.Room
import com.kashif.core.data.local.MedicineDatabase
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.data.respository.MedicineRepositoryImpl
import com.kashif.core.domain.repository.MedicineRepository
import com.kashif.core.domain.usecase.FetchMedicinesUseCase
import com.kashif.core.domain.usecase.GetMedicineByIdUseCase
import com.kashif.core.domain.usecase.GetMedicinesUseCase
import com.kashif.core.domain.usecase.InsertMedicinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    private const val DATABASE_NAME = "medicine_database"

    @Provides
    @Singleton
    fun provideMedicineDatabase(@ApplicationContext context: Context): MedicineDatabase {
        return Room.databaseBuilder(
            context,
            MedicineDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(
        db: MedicineDatabase,
        api: MedicineApi
    ): MedicineRepository {
        return MedicineRepositoryImpl(db.medicineDao, api)
    }

    @Provides
    @Singleton
    fun provideGetMedicinesUseCase(repository: MedicineRepository): GetMedicinesUseCase {
        return GetMedicinesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMedicineByIdUseCase(repository: MedicineRepository): GetMedicineByIdUseCase {
        return GetMedicineByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertMedicinesUseCase(repository: MedicineRepository): InsertMedicinesUseCase {
        return InsertMedicinesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMedicinesUseCase(repository: MedicineRepository): FetchMedicinesUseCase {
        return FetchMedicinesUseCase(repository)
    }
}