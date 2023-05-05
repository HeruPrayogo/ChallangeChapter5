package com.example.challangechapter5.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [RoomData::class], version = 1)
abstract class DatabaseAccount: RoomDatabase() {
    abstract fun noteDao(): RoomDao

    companion object{
        private var INSTANCE : DatabaseAccount? = null

        fun getInstance(context: Context): DatabaseAccount? {
            if (INSTANCE == null){
                synchronized(DatabaseAccount::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseAccount::class.java,"Note.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }


    }
}