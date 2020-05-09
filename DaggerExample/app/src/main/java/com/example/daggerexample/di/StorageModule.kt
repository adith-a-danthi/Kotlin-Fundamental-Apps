package com.example.daggerexample.di

import com.example.daggerexample.storage.SharedPreferencesStorage
import com.example.daggerexample.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    // Makes Dagger provide SharedPreferenceStorage when Storage type is requested
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}