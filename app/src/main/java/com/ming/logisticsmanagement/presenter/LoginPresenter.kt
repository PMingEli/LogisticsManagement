package com.ming.logisticsmanagement.presenter

import com.ming.logisticsmanagement.contract.LoginContract
import com.ming.logisticsmanagement.extensions.isValidPassword
import com.ming.logisticsmanagement.extensions.isValidUserName

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()){
            //用户名合法，继续校验密码
            if (password.isValidPassword()){
                //密码合法，开始登陆
                view.onStartLogin()
            }
            else view.onPasswordError()
        }else view.onUserNameError()
    }
}