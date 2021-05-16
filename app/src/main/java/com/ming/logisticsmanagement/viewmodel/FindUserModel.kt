package com.ming.logisticsmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ming.logisticsmanagement.User

class FindUserModel : ViewModel(){
    var findUser:LiveData<User>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
}