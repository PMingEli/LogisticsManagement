package com.ming.logisticsmanagement.WaybillRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ming.logisticsmanagement.UserRoom.User
import com.ming.logisticsmanagement.WaybillRoom.Waybill

@Dao
interface WaybillDao {
    @Insert
    fun insertWaybills(vararg waybills: Waybill?)

    @Query("SELECT * FROM Waybill WHERE consignor In(:consignor)")
    fun getConsignor(consignor:String): LiveData<Waybill>

    @Query("SELECT * FROM Waybill")
    fun getAllWaybills():List<Waybill>
}