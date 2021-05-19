package com.ming.logisticsmanagement.ui.activity

import android.view.View
import android.widget.TextView
import com.ming.logisticsmanagement.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity() {

    private val userName:String? by lazy { intent.getStringExtra("userName") }
    private val password:String? by lazy { intent.getStringExtra("password") }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        enter_waybill.setOnClickListener {
            startActivity<EnterwaybillActivity>("userName" to userName, "password" to password)
            finish()
        }//录入运单
        query_local_waybill.setOnClickListener {
            startActivity<LocalwaybillActivity>("userName" to userName, "password" to password,"page" to "local")
            finish()
        }//本地运单
        query_xml_waybill.setOnClickListener {
            startActivity<LocalwaybillActivity>("userName" to userName, "password" to password,"page" to "xml")
            finish()
        }//xml运单
        query_json_waybill.setOnClickListener {
            startActivity<LocalwaybillActivity>("userName" to userName, "password" to password,"page" to "json")
            finish()
        }//json运单
        switch_user.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }
        quit.setOnClickListener { finish() }
        accountmsgshow()

    }

    fun accountmsgshow(){

        val text_userName = findViewById<View>(R.id.userName) as TextView
        val text_password = findViewById<View>(R.id.password) as TextView
        text_userName.text = "我是"+ userName
        text_password.text = "我的密码是"+ password
    }

}
