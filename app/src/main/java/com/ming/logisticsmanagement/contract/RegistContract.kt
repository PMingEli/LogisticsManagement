package com.ming.logisticsmanagement.contract

interface RegistContract {
    interface View{
        fun onUserNameError()
        fun onPasswordError()
    }
}