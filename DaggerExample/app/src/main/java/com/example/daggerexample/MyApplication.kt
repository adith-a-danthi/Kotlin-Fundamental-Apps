package com.example.daggerexample

import android.app.Application
import com.example.daggerexample.storage.SharedPreferencesStorage
import com.example.daggerexample.user.UserManager

open class MyApplication : Application() {

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}
