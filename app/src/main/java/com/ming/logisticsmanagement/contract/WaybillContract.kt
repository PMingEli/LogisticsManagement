package com.ming.logisticsmanagement.contract

interface WaybillContract {
    interface Presenter:BasePresenter{
        fun loadWaybills()
    }

    interface View{
        fun onLoadWaybillsSuccess()
        fun onLoadWaybillsFailed()
    }
}