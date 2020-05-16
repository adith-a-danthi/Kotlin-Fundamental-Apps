package com.example.daggerexample.di

import android.content.Context
import com.example.daggerexample.login.LoginComponent
import com.example.daggerexample.main.MainActivity
import com.example.daggerexample.registration.RegistrationComponent
import com.example.daggerexample.registration.enterdetails.EnterDetailsFragment
import com.example.daggerexample.settings.SettingsActivity
import com.example.daggerexample.user.UserComponent
import com.example.daggerexample.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance the content passed will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Declaring a function that returns a type allows retrieving types from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    fun userManager(): UserManager

    // Classes that can be injected by this component
    // Declaring a function that returns Unit and takes a class as a parameter allows field injection in that class
/*    fun inject(activity: MainActivity)
    fun inject(activity: SettingsActivity)*/
    // Classes are injected in RegistrationComponent
/*
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
*/

}