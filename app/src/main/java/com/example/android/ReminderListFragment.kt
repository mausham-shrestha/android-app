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
import com.example.android.databinding.FragmentReminderListFragmentBinding


class ReminderListFragment : Fragment() {

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
}