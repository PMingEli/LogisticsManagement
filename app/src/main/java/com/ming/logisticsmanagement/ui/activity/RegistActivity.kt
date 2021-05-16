package com.ming.logisticsmanagement.ui.activity

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.UserRoom.User
import com.ming.logisticsmanagement.contract.RegistContract
import com.ming.logisticsmanagement.UserRoom.UserDao
import com.ming.logisticsmanagement.extensions.isValidPassword
import com.ming.logisticsmanagement.extensions.isValidUserName
import com.ming.logisticsmanagement.viewmodel.FindUserModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regist.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegistActivity : BaseActivity(), RegistContract.View {

    lateinit var  userviewModel: FindUserModel
    var registFlag:Boolean=false

    override fun getLayoutResId(): Int = R.layout.activity_regist

    override fun initData() {
        super.initData()
        userviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindUserModel::class.java)
    }

    override fun init() {
        super.init()
        regist.setOnClickListener{RegistUser()}
    }

    fun RegistUser(){
        //会自动绑定get方法 插入成功后会自动get 设置个变量
        registFlag = true
        var textcount = input_account.text.trim().toString()
        var textpws = input_password.text.trim().toString()
        var textpwsre = input_passwordreput.text.trim().toString()
        var textcity = input_city.text.trim().toString()
        if(textcount.isValidUserName()){
            if (textpws.isValidPassword()){
                if (textpwsre.isValidPassword()){
                    if (!textpwsre.equals(textpws)){
                        toast("两次密码输入不一致")
                    }
                    if (textcount.isNotEmpty() && textpws.isNotEmpty() && textpwsre.isNotEmpty() &&textpwsre.equals(textpws)&&textcity.isNotEmpty()){
                        userviewModel.findUser = userdao.getUser(textcount)

                        userviewModel.findUser?.let {
                            userviewModel.findUser!!.observe(this, Observer {
                                if (registFlag){
                                    if ((it!=null)){
                                        input_account.text.clear()
                                        toast("该账户已存在")
                                    }else{
                                        registFlag= false
                                        var user = User(textcount,textpws,textcity)
                                        InsertAsyncTask(userdao).execute(user)
                                        //增设查询该条记录
                                        toast("插入成功")
                                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                                            startActivity<LoginActivity>()
                                            finish()
                                        },1000)
                                    }
                                }
                            })
                        }
                    }
                }else onPasswordError()
            }else onPasswordError()
        }else onUserNameError()
    }

    internal class InsertAsyncTask(private val userdao: UserDao):AsyncTask<User?,Unit,Unit>(){
        override fun doInBackground(vararg params: User?) {
            publishProgress()
            userdao.insertUsers(*params)
        }
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

}
