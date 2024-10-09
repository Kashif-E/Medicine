package com.kashif.core.di

import android.content.Context
import androidx.room.Room
import com.kashif.core.data.local.MedicineDao
import com.kashif.core.data.local.MedicineDatabase
import com.kashif.core.data.local.UserDao
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.data.respository.MedicineRepositoryImpl
import com.kashif.core.data.respository.UserRepositoryImpl
import com.kashif.core.domain.repository.IMedicineRepository
import com.kashif.core.domain.repository.IUserRepository
import com.kashif.core.domain.usecase.FetchMedicinesUseCase
import com.kashif.core.domain.usecase.GetLoggedInUserUseCase
import com.kashif.core.domain.usecase.GetMedicineByIdUseCase
import com.kashif.core.domain.usecase.GetMedicinesUseCase
import com.kashif.core.domain.usecase.InsertMedicinesUseCase
import com.kashif.core.domain.usecase.InsertUserUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
        medicineApi: MedicineApi,
        @ioDispatcher ioDispatcher: CoroutineDispatcher
    ): MedicineRepositoryImpl {
        return MedicineRepositoryImpl(dao, medicineApi, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MedicineDatabase): UserDao {
        return database.userDao
    }

    @Provides
    @Singleton
    fun provideUserRepository(dao: UserDao): UserRepositoryImpl {
        return UserRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideInsertUserUseCase(userRepository: IUserRepository): InsertUserUseCase {
        return InsertUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetLoggedInUserUseCase(userRepository: IUserRepository): GetLoggedInUserUseCase {
        return GetLoggedInUserUseCase(userRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface IMedicineRepositoryModule {
    @Binds
    fun bindIMedicineRepository(
        medicineRepositoryImpl: MedicineRepositoryImpl
    ): IMedicineRepository

    @Binds
    fun bindIUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): IUserRepository
}
