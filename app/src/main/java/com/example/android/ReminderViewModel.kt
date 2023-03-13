/*
 * *
 *  * Created by maushamshrestha on 13/3/23, 5:47 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 13/3/23, 5:47 pm
 *
 */

package com.example.android

import androidx.lifecycle.*
import com.example.android.data.Reminder
import com.example.android.data.ReminderDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *
 * View model to keep a reference to the Reminder repository
 * and up-to-date list of all reminders
 *
 */
class ReminderViewModel(private val reminderDao: ReminderDao) : ViewModel() {

    val allReminders: LiveData<List<Reminder>> = reminderDao.getReminders().asLiveData()

    fun updateReminder(
        reminderId: Int,
        reminderName: String,
        reminderDescription:String,
        reminderDate: String
    ) {
        val updatedReminder = Reminder(
            reminderId,
            reminderName,
            reminderDescription,
            reminderDate
        )

        viewModelScope.launch {
            reminderDao.update(updatedReminder)
        }
    }

    fun addNewReminder(
        reminderName: String,
        reminderDescription: String,
        reminderDate: String
    ) {
        val reminder = getNewReminderEntry(reminderName, reminderDescription, reminderDate)
        insertReminder(reminder = reminder)
    }

    private fun getNewReminderEntry(
        reminderName: String,
        reminderDescription: String,
        reminderDate: String): Reminder {

        return Reminder(
            reminderName = reminderName,
            reminderDescription = reminderDescription,
            reminderDate = reminderDate
        )
    }

    private fun insertReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderDao.insert(reminder)
        }
    }

    fun retrieveReminder(id: Int): LiveData<Reminder> {
        return reminderDao.getReminder(id).asLiveData()
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class ReminderViewModelFactory(private val reminderDao: ReminderDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(reminderDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}