package com.example.constructioncalculator

import android.app.Application
import com.example.constructioncalculator.di.AppComponent
import com.example.constructioncalculator.di.DaggerAppComponent

class MainApp: Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(this) }
}