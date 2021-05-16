package com.ming.logisticsmanagement.WaybillRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Waybill::class],version = 1,exportSchema = false)
abstract class WaybillDatabase :RoomDatabase() {
    companion object{
        var INSTANCE: WaybillDatabase?=null

        @Synchronized
        open fun getDatabase(context: Context): WaybillDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    WaybillDatabase::class.java,"waybill_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun getWaybillDao(): WaybillDao

}