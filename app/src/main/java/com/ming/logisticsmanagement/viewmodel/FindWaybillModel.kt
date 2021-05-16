package com.ming.logisticsmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ming.logisticsmanagement.WaybillRoom.Waybill

class FindWaybillModel : ViewModel() {
    var findWaybill:LiveData<Waybill>?=null
        get() {
            if (field == null){
                field = MutableLiveData()
            }
            return field
        }
}