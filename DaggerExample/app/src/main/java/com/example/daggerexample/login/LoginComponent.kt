package com.example.daggerexample.login

import com.example.daggerexample.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }

    // Classes injected by this component
    fun inject(activity: LoginActivity)

}