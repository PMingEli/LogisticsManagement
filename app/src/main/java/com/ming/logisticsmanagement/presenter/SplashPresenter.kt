package com.ming.logisticsmanagement.presenter

import com.ming.logisticsmanagement.contract.SplashContract

class SplashPresenter(val view: SplashContract.View) :SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false
}