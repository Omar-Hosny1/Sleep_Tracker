package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter(val clickListener: SleepNightListener) :
    ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDifCallback()) {

    // ViewGroup is the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    // recycling did here, and drawing
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // this fun to how to update the views
        fun bind(
            item: SleepNight,
            clickListener: SleepNightListener
        ) {
            binding.sleep = item
            binding.clickListener = clickListener
            // force the binding object to evaluate the changes immediately
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDifCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // check the equality by checking the all vars in this data class
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (SleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}