/*
 * *
 *  * Created by Mausham on 28/1/23, 10:55 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 28/1/23, 10:53 pm
 *
 */

package com.example.android

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.data.Reminder
import com.example.android.databinding.FragmentAddReminderFragmentBinding
import com.example.android.databinding.FragmentReminderListFragmentBinding


class AddReminderFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val viewModel: ReminderViewModel by activityViewModels {
        ReminderViewModelFactory(
            (activity?.application as ReminderApplication).database
                .reminderDao()
        )
    }

    private var _binding: FragmentAddReminderFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddReminderFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reminderDatePicker.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val month = i2 + 1
            binding.reminderDateSelected.setText("$i/$month/$i3")
        }

        binding.saveReminder.setOnClickListener {
            addNewReminder()
        }
    }

    private fun addNewReminder() {
        viewModel.addNewReminder(
            binding.reminderName.text.toString(),
            binding.reminderDescription.text.toString(),
            binding.reminderDateSelected.text.toString()
        )

        val action = AddReminderFragmentDirections.actionAddReminderFragmentToReminderListFragment()
        findNavController().navigate(action)

        Toast.makeText(context, "Reminder added", Toast.LENGTH_SHORT).show()
    }
}