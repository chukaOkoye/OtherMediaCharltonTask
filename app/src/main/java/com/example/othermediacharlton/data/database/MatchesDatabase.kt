package com.example.othermediacharlton.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FixturesEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MatchesDatabase : RoomDatabase(){

    abstract fun matchesDao(): MatchesDao

    companion object {

        private lateinit var database: MatchesDatabase
        fun getInstance(context: Context): MatchesDatabase {
            if (this::database.isInitialized) {
                return database
            }
            return Room.databaseBuilder(
                context.applicationContext,
                MatchesDatabase::class.java,
                "matchesDatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also {
                    database = it
                }
        }
    }
}