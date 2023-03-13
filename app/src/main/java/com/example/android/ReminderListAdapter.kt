/*
 * *
 *  * Created by maushamshrestha on 13/3/23, 7:20 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 13/3/23, 7:20 pm
 *
 */

package com.example.android

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.data.Reminder
import com.example.android.databinding.FragmentReminderDetailFragmentBinding
import com.example.android.databinding.FragmentReminderItemFragmentBinding
import com.example.android.databinding.FragmentReminderListFragmentBinding

class ReminderListAdapter(private val onItemClicked: (Reminder) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Reminder, ReminderListAdapter.ReminderViewHolder>(
        DiffCallback
    ) {

    class ReminderViewHolder(private var binding: FragmentReminderItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder) {
            binding.reminderName.text = reminder.reminderName
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder(
            FragmentReminderItemFragmentBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }

        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Reminder>() {
            override fun areItemsTheSame(oldReminder: Reminder, newReminder: Reminder): Boolean {
                return oldReminder === newReminder
            }

            override fun areContentsTheSame(oldReminder: Reminder, newReminder: Reminder): Boolean {
                return oldReminder.reminderName == newReminder.reminderName
            }
        }
    }
}