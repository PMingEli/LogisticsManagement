package com.ming.logisticsmanagement.WaybillRoom

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Waybill(
    @ColumnInfo(name = "consignor")
    val consignor:String,
    @ColumnInfo(name = "consignorPhoneNumber")
    val consignorPhoneNumber:String,
    @ColumnInfo(name = "consignee")
    val consignee:String,
    @ColumnInfo(name = "consigneePhoneNumber")
    val consigneePhoneNumber:String,
    @ColumnInfo(name = "transportationDepartureStation")
    val transportationDepartureStation:String,
    @NonNull
    @ColumnInfo(name = "transportationArrivalStation")
    val transportationArrivalStation:String,
    @NonNull
    @ColumnInfo(name = "goodsName")
    val goodsName:String,
    @NonNull
    @ColumnInfo(name = "numberOfPackages")
    val numberOfPackages:String,
    @ColumnInfo(name = "FreightPaidByConsignor")
    val FreightPaidByConsignor:String,
    @ColumnInfo(name = "FreightPaidByConsignee")
    val FreightPaidByConsignee:String
){
    @PrimaryKey(autoGenerate = true)
    var waybillNo:Long=1000L
}