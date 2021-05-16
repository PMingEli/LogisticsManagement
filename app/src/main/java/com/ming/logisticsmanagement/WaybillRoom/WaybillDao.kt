package com.ming.logisticsmanagement.WaybillRoom

import androidx.room.Dao
import androidx.room.Insert
import com.ming.logisticsmanagement.WaybillRoom.Waybill

@Dao
interface WaybillDao {
    @Insert
    fun insertWaybills(vararg waybills: Waybill?)

}