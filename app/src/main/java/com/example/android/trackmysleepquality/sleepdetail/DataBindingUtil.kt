package com.example.android.trackmysleepquality.sleepdetail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight?) {
    item?.let {
        setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_launcher_sleep_tracker_background
        })
    }
}

@BindingAdapter("startTimeTxt")
fun TextView.setStartTimeText(sleepNight: SleepNight?){
    sleepNight?.let {
        text = sleepNight.startTimeMilli.toString()
    }
}

@BindingAdapter("endTimeTxt")
fun TextView.setEndTimeText(sleepNight: SleepNight?){
    sleepNight?.let {
        text = sleepNight.endTimeMilli.toString()
    }
}