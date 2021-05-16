package com.ming.logisticsmanagement

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ming.logisticsmanagement.contract.UserDao

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    companion object{
        var INSTANCE:UserDatabase? = null

        @Synchronized
        open fun getDatabase(context: Context): UserDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"user_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun getUserDao(): UserDao
}