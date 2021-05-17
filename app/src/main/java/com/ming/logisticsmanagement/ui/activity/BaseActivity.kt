package com.ming.logisticsmanagement.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.ming.logisticsmanagement.UserRoom.UserDatabase
import com.ming.logisticsmanagement.WaybillRoom.WaybillDatabase
import com.ming.logisticsmanagement.UserRoom.UserDao
import com.ming.logisticsmanagement.WaybillRoom.WaybillDao

abstract class BaseActivity : AppCompatActivity() {

    private var userdatabase: UserDatabase? = null
    lateinit var userdao: UserDao
    private var waybilldatabase: WaybillDatabase? = null
    lateinit var waybilldao: WaybillDao

    val progressDialog by lazy {
        ProgressDialog(this)
    }

    val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initData()
        init()
    }

    //初始化数据
    open fun initData(){
        userdatabase= UserDatabase.getDatabase(applicationContext)!!
        userdao=userdatabase?.getUserDao()!!
        waybilldatabase = WaybillDatabase.getDatabase(applicationContext)!!
        waybilldao = waybilldatabase?.getWaybillDao()!!
    }

    open fun init(){
        //初始化一些公共的功能，子类也可以复写该方法，完成自己的初始化
    }


    //子类必须实现方法返回一个布局资源的id
    abstract fun getLayoutResId(): Int

    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){
        progressDialog.dismiss()
    }

    fun hideSoftKeyboard(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }
}