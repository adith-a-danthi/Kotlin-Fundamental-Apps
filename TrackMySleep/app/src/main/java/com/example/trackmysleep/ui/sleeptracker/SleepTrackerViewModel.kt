package com.example.trackmysleep.ui.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.trackmysleep.database.SleepDatabaseDao

class SleepTrackerViewModel(
    val  dao: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}