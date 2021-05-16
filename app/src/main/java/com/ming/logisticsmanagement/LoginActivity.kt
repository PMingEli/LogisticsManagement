package com.ming.logisticsmanagement

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ming.logisticsmanagement.contract.LoginContract
import com.ming.logisticsmanagement.presenter.LoginPresenter
import com.ming.logisticsmanagement.viewmodel.FindUserModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(),LoginContract.View {

    lateinit var  userviewModel: FindUserModel
    var loginFlag:Boolean=false

    val presenter = LoginPresenter(this)
    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initData() {
        super.initData()
        userviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindUserModel::class.java)
    }

    override fun init() {
        super.init()
        btn_login.setOnClickListener { login() }
        quit.setOnClickListener { finish() }
        newUser.setOnClickListener{
            startActivity<RegistActivity>()
            finish()
        }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }
    fun login(){
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        presenter.login(userNameString, passwordString)
        loginFlag = true
        if (!userNameString.isEmpty()&&!passwordString.isEmpty()){
            userviewModel.findUser = userdao.getUser(userNameString)

            userviewModel.findUser?.let{
                userviewModel.findUser!!.observe(this, Observer {
                    if(loginFlag){
                        if ((it!=null)){
                            if (it.password.equals(passwordString)){
                                toast("登陆成功")
                                onLoggedInSuccess(userNameString,passwordString)
                            }else{
                                onLoggedInFailed()
                            }
                        }else{
                            toast("用户不存在")
                        }
                    }
                })
            }
        }
    }



    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        //弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInSuccess(userName:String,password:String) {
        //隐藏进度条
        dismissProgress()
        //进入主界面
        startActivity<MainActivity>("userName" to userName, "password" to password)
        //退出LoginActivity
        finish()
    }

    override fun onLoggedInFailed() {
        //隐藏进度条
        dismissProgress()
        //弹出Toast
        toast(R.string.login_failed)
    }

    fun IntoRegist(v: View){
        startActivity<RegistActivity>()
    }

}