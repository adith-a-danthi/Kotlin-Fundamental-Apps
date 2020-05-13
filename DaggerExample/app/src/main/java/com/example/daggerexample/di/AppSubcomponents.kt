package com.example.daggerexample.di

import com.example.daggerexample.registration.RegistrationComponent
import dagger.Module

@Module(subcomponents = [RegistrationComponent::class])
class AppSubcomponents {
}