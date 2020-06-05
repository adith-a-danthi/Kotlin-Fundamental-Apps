package com.example.trackmysleep.ui.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sleep_tracker.*

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sleep_tracker, container,false)
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
                textView.text = it.toString()
            } else {
                textView.text = " "
            }
        })

        start_button.setOnClickListener { viewModel.onStartTracking() }
        stop_button.setOnClickListener { viewModel.onStopTracking() }
        clear_button.setOnClickListener { viewModel.onClear() }

        viewModel.navigateToSleepQuality.observe(requireActivity(), Observer { night ->
            night?.let {
                // Type Unsafe way
                val bundle: Bundle = bundleOf("sleepNightKey" to night.nightId)
                this.findNavController().navigate(R.id.action_sleep_tracker_fragment_to_sleepQualityFragment, bundle)

                // TypeSafe Way
                // this.findNavController().navigate(SleepTrackerFragmentDirections.action_sleep_tracker_fragment_to_sleepQualityFragment(night.nightId))
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