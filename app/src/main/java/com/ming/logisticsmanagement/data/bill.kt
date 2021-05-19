package com.ming.logisticsmanagement.data

class bill(waybillNo:String,consignor:String,consignorPhoneNumber:String,consignee:String,consigneePhoneNumber:String,
           transportationDepartureStation:String,transportationArrivalStation:String,goodsName:String,
           numberOfPackages:String,freightPaidByTheReceivingParty:String,freightPaidByConsignor:String) {
    val waybillNo = waybillNo
    val consignor = consignor
    val consignorPhoneNumber = consignorPhoneNumber
    val consignee = consignee
    val consigneePhoneNumber = consigneePhoneNumber
    val transportationDepartureStation = transportationDepartureStation
    val transportationArrivalStation =transportationArrivalStation
    val goodsName =goodsName
    val numberOfPackages = numberOfPackages
    val freightPaidByTheReceivingParty = freightPaidByTheReceivingParty
    val freightPaidByConsignor = freightPaidByConsignor
}