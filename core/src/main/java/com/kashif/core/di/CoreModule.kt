package com.kashif.core.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kashif.core.data.local.MedicineDao
import com.kashif.core.data.local.MedicineDatabase
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.data.respository.MedicineRepositoryImpl
import com.kashif.core.domain.repository.IMedicineRepository
import com.kashif.core.domain.usecase.FetchMedicinesUseCase
import com.kashif.core.domain.usecase.GetMedicineByIdUseCase
import com.kashif.core.domain.usecase.GetMedicinesUseCase
import com.kashif.core.domain.usecase.InsertMedicinesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    fun provideMedicineDao(database: MedicineDatabase): MedicineDao {
        return database.medicineDao
    }

    @Provides
    @Singleton
    fun provideGetMedicinesUseCase(repository: IMedicineRepository): GetMedicinesUseCase {
        return GetMedicinesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMedicineByIdUseCase(repository: IMedicineRepository): GetMedicineByIdUseCase {
        return GetMedicineByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertMedicinesUseCase(repository: IMedicineRepository): InsertMedicinesUseCase {
        return InsertMedicinesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMedicinesUseCase(repository: IMedicineRepository): FetchMedicinesUseCase {
        return FetchMedicinesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideIMedicineRepository(
        dao: MedicineDao,
        medicineApi: MedicineApi
    ): MedicineRepositoryImpl {
        return MedicineRepositoryImpl(dao, medicineApi)
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface IMedicineRepositoryModule {
    @Binds
    fun bindIMedicineRepository(
        medicineRepositoryImpl: MedicineRepositoryImpl
    ): IMedicineRepository
}
