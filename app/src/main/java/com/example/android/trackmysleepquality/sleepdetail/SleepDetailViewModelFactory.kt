package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao

class SleepDetailViewModelFactory(private val nightId: Long,private val dataSource: SleepDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepDetailViewModel::class.java)){
            return  SleepDetailViewModel(nightId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}