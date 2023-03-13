/*
 * *
 *  * Created by maushamshrestha on 12/3/23, 11:07 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 12/3/23, 11:07 pm
 *
 */

package com.example.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderRoomDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: ReminderRoomDatabase? = null
        fun getDatabase(context: Context): ReminderRoomDatabase {
            return INSTANCE ?: synchronized(this)  {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderRoomDatabase::class.java,
                    "reminder_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE= instance

                instance
            }
        }
    }

}