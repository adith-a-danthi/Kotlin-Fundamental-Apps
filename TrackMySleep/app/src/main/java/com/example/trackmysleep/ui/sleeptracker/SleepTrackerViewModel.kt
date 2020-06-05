package com.example.trackmysleep.ui.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.trackmysleep.database.SleepDatabaseDao
import com.example.trackmysleep.database.SleepNight
import com.example.trackmysleep.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(
    val  dao: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight: MutableLiveData<SleepNight?> = MutableLiveData<SleepNight?>()

    private var _navigateToSleepQuality = MutableLiveData<SleepNight>()

    val navigateToSleepQuality: LiveData<SleepNight> get() = _navigateToSleepQuality

    private val nights = dao.getAllNights()

    val nightString = Transformations.map(nights) { nights ->
        formatNights(nights = nights, resources = application.resources)
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackbarEvent: LiveData<Boolean> get() = _showSnackbarEvent

    // Coroutines for db operations
    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDb()
        }
    }

    private suspend fun getTonightFromDb(): SleepNight? {
        return withContext(Dispatchers.IO){
            var night: SleepNight? = dao.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli){
                night = null
            }
            night
        }
    }

    // Coroutines for the button operations
    fun onStartTracking() {
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDb()
        }
    }

    private suspend fun insert(newNight: SleepNight) {
        withContext(Dispatchers.IO){
            dao.insert(newNight)
        }
    }

    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }

    private suspend fun update(oldNight: SleepNight){
        withContext(Dispatchers.IO){
            dao.update(oldNight)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null

            _showSnackbarEvent.value = true
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO){
            dao.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}