package com.kashif.medicine

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp(Application::class)
class MedicineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}