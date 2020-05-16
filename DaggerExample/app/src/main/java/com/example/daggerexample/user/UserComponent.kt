package com.example.daggerexample.user

import com.example.daggerexample.main.MainActivity
import com.example.daggerexample.settings.SettingsActivity
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(activity: SettingsActivity)
    fun inject(activity: MainActivity)
}