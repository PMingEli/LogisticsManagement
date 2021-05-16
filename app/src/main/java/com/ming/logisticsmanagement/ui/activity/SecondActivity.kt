package com.ming.logisticsmanagement.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ming.logisticsmanagement.R


class SecondActivity:Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val account = intent.getStringExtra("Name")
        val password = intent.getStringExtra("Password")
        val textView1 = findViewById<View>(R.id.text_account) as TextView
        val textView2 = findViewById<View>(R.id.text_password) as TextView
        textView1.text = "我是" + account
        textView2.text = "我的密码是" + password

        findViewById<Button>(R.id.btn_signin).setOnClickListener(this)
        findViewById<Button>(R.id.btn_back).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signin ->{
                val intent = Intent().setClass(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_back ->{
                //ActivityCollector.finishAll()
                exitProcess()
            }


        }
    }
    private fun exitProcess() {
        System.exit(-1)

    }
}

