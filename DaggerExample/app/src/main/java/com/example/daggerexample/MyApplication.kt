package com.example.daggerexample

import android.app.Application
import com.example.daggerexample.di.AppComponent
import com.example.daggerexample.di.DaggerAppComponent
import com.example.daggerexample.storage.SharedPreferencesStorage
import com.example.daggerexample.user.UserManager

open class MyApplication : Application() {
    // Instance of AppComponent that will be used by all activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its factory constructor
        // The applicationContext passed will be used as context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }


    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}
