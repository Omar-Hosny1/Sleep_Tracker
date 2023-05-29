package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight

class SleepDetailViewModel(private val nightId: Long = 0L, dataSource: SleepDatabaseDao) :
    ViewModel() {
    val database = dataSource

    private val night = MediatorLiveData<SleepNight>()
    fun getNight() = night

    init {
        night.addSource(dataSource.getNightWithId(nightId), night::setValue)
    }
    private val _navigateToSleepTracker = MutableLiveData<Boolean>()
    val navigateToSleepTracker: LiveData<Boolean>
        get() = _navigateToSleepTracker

    fun doneNavigateToSleepTracker(){
        _navigateToSleepTracker.value = null
    }

    fun onClose(){
        _navigateToSleepTracker.value = true
    }

}