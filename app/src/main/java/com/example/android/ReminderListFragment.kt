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
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.databinding.FragmentReminderItemFragmentBinding
import com.example.android.databinding.FragmentReminderListFragmentBinding
import kotlinx.coroutines.flow.observeOn

/**
 * Main Fragment displaying all the reminder saved in the database
 */
class ReminderListFragment : Fragment() {
    private val viewModel: ReminderViewModel by activityViewModels {
        ReminderViewModelFactory(
            (activity?.application as ReminderApplication).database.reminderDao()
        )
    }
    private var _binding: FragmentReminderListFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReminderListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReminderListAdapter {
            val action = ReminderListFragmentDirections.actionReminderListFragmentToReminderDetailFragment(it.id)
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.allReminders.observe(this.viewLifecycleOwner) {
            reminders ->  reminders.let {
                adapter.submitList(it)
            }
        }
        binding.createNewReminderBtn.setOnClickListener {
            val action = ReminderListFragmentDirections.actionReminderListFragmentToAddReminderFragment()
            this.findNavController().navigate(action)
        }
    }
}