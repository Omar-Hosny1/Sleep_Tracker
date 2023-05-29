package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackBarEvent

    fun doneShowSnackBar() {
        _showSnackBarEvent.value = false
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<SleepNight?>()

    val nights = database.getAllNights()

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

    val clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }

    val startButtonVisible = Transformations.map(tonight) { night ->
        night == null
    }
    val stopButtonVisible = Transformations.map(tonight) { night ->
        night != null
    }

    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    val nightString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    private fun initializeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if (night?.startTimeMilli != night?.endTimeMilli) {
                night = null
            }
            night
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(night)
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

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackBarEvent.value = true
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    init {
        initializeTonight()
    }
    private val _navigateToSleepDataQuality = MutableLiveData<Long>()
    val navigateToSleepDataQuality:LiveData<Long>
        get() = _navigateToSleepDataQuality

    fun onSleepNightClicked(nightId: Long){
        _navigateToSleepDataQuality.value = nightId
    }
    fun onToSleepDataNaviated(){
        _navigateToSleepDataQuality.value = null
    }
}
