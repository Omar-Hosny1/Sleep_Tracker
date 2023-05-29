package com.example.android.trackmysleepquality.sleepdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.FragmentSleepDetailBinding
import com.example.android.trackmysleepquality.sleepquality.SleepQualityFragmentDirections

class SleepDetailFragment : Fragment() {
    lateinit var binding: FragmentSleepDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail, container, false)
        binding.setLifecycleOwner(this)

        val nightId: Long = SleepDetailFragmentArgs.fromBundle(arguments!!).nightId
        val application = requireNotNull(activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val ViewModelFactory = SleepDetailViewModelFactory(nightId, dataSource)
        val viewModel =
            ViewModelProviders.of(this, ViewModelFactory).get(SleepDetailViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                viewModel.doneNavigateToSleepTracker()
            }
        })
        return binding.root
    }
}