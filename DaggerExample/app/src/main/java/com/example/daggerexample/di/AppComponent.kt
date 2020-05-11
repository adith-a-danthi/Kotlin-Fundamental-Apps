package com.example.daggerexample.di

import android.content.Context
import com.example.daggerexample.main.MainActivity
import com.example.daggerexample.registration.RegistrationActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [StorageModule::class])
interface AppComponent {

    // Factory to create instances of AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance the content passed will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can be injected by this component
    fun inject(activity: RegistrationActivity)
    fun inject(activity: MainActivity)
}