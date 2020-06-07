package com.example.trackmysleep.ui.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.databinding.FragmentSleepQualityBinding


class SleepQualityFragment : Fragment() {

    private lateinit var binding: FragmentSleepQualityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSleepQualityBinding.inflate(inflater)
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_sleep_quality, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val dao = SleepDatabase.getInstance((this.activity)!!.application).sleepDatabaseDao

        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dao)
        val sleepQualityViewModel =
            ViewModelProvider(this, viewModelFactory).get(SleepQuaityViewModel::class.java)
        
        binding.qualityZeroImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(0) }
        binding.qualityOneImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(1) }
        binding.qualityTwoImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(2) }
        binding.qualityThreeImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(3) }
        binding.qualityFourImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(4) }
        binding.qualityFiveImage.setOnClickListener { sleepQualityViewModel.onSetSleepQuality(5) }

        sleepQualityViewModel.navigateToSleepTracker.observe(requireActivity(), Observer {
            if (it == true) {
                this.findNavController().navigate(R.id.action_sleepQualityFragment_to_sleep_tracker_fragment)
                sleepQualityViewModel.doneNavigating()
            }
        })

    }

}
