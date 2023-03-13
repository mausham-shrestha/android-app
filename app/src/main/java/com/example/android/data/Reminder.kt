/*
 * *
 *  * Created by Mausham Shrestha on 12/3/23, 10:45 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 12/3/23, 10:45 pm
 *
 */

package com.example.android.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val reminderName: String,
    @ColumnInfo(name = "description") val reminderDescription: String,
    @ColumnInfo(name = "date") val reminderDate: String
)