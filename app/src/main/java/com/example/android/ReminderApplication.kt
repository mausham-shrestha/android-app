/*
 * *
 *  * Created by maushamshrestha on 13/3/23, 6:49 pm
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 13/3/23, 6:49 pm
 *
 */

package com.example.android

import android.app.Application
import com.example.android.data.ReminderRoomDatabase

class ReminderApplication : Application() {
    val database: ReminderRoomDatabase by lazy {
        ReminderRoomDatabase.getDatabase(this)
    }
}