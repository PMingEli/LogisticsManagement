package com.ming.logisticsmanagement.WaybillRoom

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Waybill(
    consignor:String,consignorPhoneNumber:String,consignee:String,consigneePhoneNumber:String,
    transportationDepartureStation:String,transportationArrivalStation:String,goodsName:String,
    numberOfPackages:String,FreightPaidByConsignor:String,FreightPaidByConsignee:String
){
    @PrimaryKey(autoGenerate = true)
    var waybillNo:Long=1000L

    @ColumnInfo(name = "consignor")
    val consignor:String = consignor

    @ColumnInfo(name = "consignorPhoneNumber")
    val consignorPhoneNumber:String = consignorPhoneNumber

    @ColumnInfo(name = "consignee")
    val consignee:String = consignee

    @ColumnInfo(name = "consigneePhoneNumber")
    val consigneePhoneNumber:String = consigneePhoneNumber

    @ColumnInfo(name = "transportationDepartureStation")
    val transportationDepartureStation:String = transportationDepartureStation

    @NonNull
    @ColumnInfo(name = "transportationArrivalStation")
    val transportationArrivalStation:String = transportationArrivalStation

    @NonNull
    @ColumnInfo(name = "goodsName")
    val goodsName:String = goodsName

    @NonNull
    @ColumnInfo(name = "numberOfPackages")
    val numberOfPackages:String = numberOfPackages

    @ColumnInfo(name = "FreightPaidByConsignor")
    val FreightPaidByConsignor:String = FreightPaidByConsignor

    @ColumnInfo(name = "FreightPaidByConsignee")
    val FreightPaidByConsignee:String = FreightPaidByConsignee
}