package com.ming.logisticsmanagement.ui.activity

import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ming.logisticsmanagement.R
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
        //隐藏软键盘
        hideSoftKeyboard()
        if (hasWriteExtenalStoragePermission()){
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
        }else applyWriteExtenalStoragePermission()




    }

    //检查是否有写磁盘的权限
    private fun hasWriteExtenalStoragePermission():Boolean {
        val result = ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result==PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //用户同意权限，开始登录
            login()
        }else{
            toast(R.string.permission_denied)
        }
    }

    private fun applyWriteExtenalStoragePermission(){
        val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
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