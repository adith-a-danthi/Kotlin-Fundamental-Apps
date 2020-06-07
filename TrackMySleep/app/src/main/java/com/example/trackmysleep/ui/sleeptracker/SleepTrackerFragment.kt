package com.example.trackmysleep.ui.sleeptracker

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
import com.example.trackmysleep.databinding.FragmentSleepTrackerBinding
import com.google.android.material.snackbar.Snackbar


class SleepTrackerFragment : Fragment() {
    private lateinit var binding: FragmentSleepTrackerBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
        binding = FragmentSleepTrackerBinding.inflate(inflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_sleep_tracker, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        viewModel.nightString.observe(requireActivity(), Observer {
            if (it != null){
                binding.textView.text = it.toString()
            } else {
                binding.textView.text = " "
            }
        })

        binding.startButton.setOnClickListener { viewModel.onStartTracking() }
        binding.stopButton.setOnClickListener { viewModel.onStopTracking() }
        binding.clearButton.setOnClickListener { viewModel.onClear() }

        viewModel.navigateToSleepQuality.observe(requireActivity(), Observer { night ->
            night?.let {
                // Type Unsafe way
//                val bundle: Bundle = bundleOf("sleepNightKey" to night.nightId)
//                this.findNavController().navigate(R.id.action_sleep_tracker_fragment_to_sleepQualityFragment, bundle)

                // TypeSafe Way
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
                viewModel.doneNavigating()
            } }
        )

        viewModel.showSnackbarEvent.observe(requireActivity(), Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()

                viewModel.doneShowingSnackbar()
            }
        })

    }
}