package com.example.hw14app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw14app.model.Word


@Database(entities = [Word::class], version = 1 )
abstract class AppDatabase : RoomDatabase(){
    abstract fun wordDao(): WordDao

    companion object{
        var instance : AppDatabase?=null
        fun getAppDatabase(context: Context):AppDatabase?{
            if (instance==null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "WordDB"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}