package com.github.kuya32.vintracker.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kuya32.vintracker.feature_auth.data.local.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class VinTrackerDatabase: RoomDatabase() {

}