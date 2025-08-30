package com.unibo.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unibo.data.local.dao.WeatherDao
import com.unibo.data.local.entities.WeatherLocalModel

@Database(entities = [WeatherLocalModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "meteosaver_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}