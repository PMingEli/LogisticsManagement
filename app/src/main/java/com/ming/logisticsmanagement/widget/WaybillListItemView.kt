package com.ming.logisticsmanagement.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import kotlinx.android.synthetic.main.view_waybill_item.view.*

class WaybillListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {
    fun bindView(waybill: Waybill, page: String) {
        leaveplace.text = waybill.transportationDepartureStation
        arrivalplace.text = waybill.transportationArrivalStation
        goodname.text = waybill.goodsName
        goodnum.text = waybill.numberOfPackages
        if(page=="xml"){
            waybillnum.text = "X"+waybill.waybillNo.toString()
        }else if(page=="json"){
            waybillnum.text = "J"+waybill.waybillNo.toString()
        }else{
            waybillnum.text = waybill.waybillNo.toString()
        }

        reciver.text = waybill.consignee
        reciverPhoneNumber.text = waybill.consigneePhoneNumber
        if (waybill.FreightPaidByConsignor.toInt()!=0){
            payway.text = "寄付"
            paymoney.text = waybill.FreightPaidByConsignor
        }else{
            payway.text = "到付"
            paymoney.text = waybill.FreightPaidByConsignee
        }
    }

    init {
            View.inflate(context, R.layout.view_waybill_item, this)
        }
}