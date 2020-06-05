package com.example.trackmysleep.ui.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import kotlinx.android.synthetic.main.fragment_sleep_quality.*


class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_quality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val dao = SleepDatabase.getInstance((this.activity)!!.application).sleepDatabaseDao

        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dao)
        val sleepQualityViewModel =
            ViewModelProvider(this, viewModelFactory).get(SleepQuaityViewModel::class.java)
        
        quality_zero_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(0) }
        quality_one_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(1) }
        quality_two_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(2) }
        quality_three_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(3) }
        quality_four_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(4) }
        quality_five_image.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(5) }

        sleepQualityViewModel.navigateToSleepTracker.observe(requireActivity(), Observer {
            if (it == true) {
                this.findNavController().navigate(R.id.action_sleepQualityFragment_to_sleep_tracker_fragment)
                sleepQualityViewModel.doneNavigating()
            }
        })

    }

}
