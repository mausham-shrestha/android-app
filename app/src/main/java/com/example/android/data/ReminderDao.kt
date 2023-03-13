/*
 * *
 *  * Created by maushamshrestha on 12/3/23, 11:00 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 12/3/23, 11:00 pm
 *
 */

package com.example.android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/**
 * Database access object to access the Reminder database
 */
@Dao
interface ReminderDao {

    @Query("SELECT * from reminder")
    fun getReminders(): Flow<List<Reminder>>

    @Query("SELECT * from reminder WHERE id = :id")
    fun getReminder(id: Int): Flow<Reminder>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)
}