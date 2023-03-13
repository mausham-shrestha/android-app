/*
 * *
 *  * Created by Mausham on 28/1/23, 10:55 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 28/1/23, 10:53 pm
 *
 */

package com.example.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.android.data.Reminder
import com.example.android.databinding.FragmentReminderDetailFragmentBinding

class ReminderDetailFragment : Fragment() {
    private val navigationArgs: ReminderDetailFragmentArgs by navArgs()
    lateinit var reminder: Reminder

    private val viewModel: ReminderViewModel by activityViewModels {
        ReminderViewModelFactory(
            (activity?.application as ReminderApplication).database.reminderDao()
        )
    }
    private var _binding: FragmentReminderDetailFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReminderDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.reminderId
        viewModel.retrieveReminder(id).observe(this.viewLifecycleOwner) {
            selectedReminder ->
            reminder = selectedReminder
            bind(reminder)
        }
    }

    private fun bind(reminder: Reminder) {
        binding.apply {
            reminderName.text = reminder.reminderName

        }
    }
}