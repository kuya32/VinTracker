package com.github.kuya32.vintracker.di

import android.app.Application
import androidx.room.Room
import com.github.kuya32.vintracker.core.data.local.VinTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesVinTrackerDatabase(app: Application): VinTrackerDatabase {
        return Room.databaseBuilder(
            app,
            VinTrackerDatabase::class.java,
            "vintrackerdb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}