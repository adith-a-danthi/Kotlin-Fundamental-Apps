package com.example.trackmysleep.ui.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        return inflater.inflate(R.layout.fragment_sleep_tracker, container,false)
    }
}