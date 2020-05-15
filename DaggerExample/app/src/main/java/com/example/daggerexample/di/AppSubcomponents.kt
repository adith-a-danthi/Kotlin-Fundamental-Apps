package com.example.daggerexample.di

import com.example.daggerexample.login.LoginComponent
import com.example.daggerexample.registration.RegistrationComponent
import dagger.Module

@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class])
class AppSubcomponents