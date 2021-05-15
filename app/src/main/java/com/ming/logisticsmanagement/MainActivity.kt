package com.ming.logisticsmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_signin).setOnClickListener(this)
        findViewById<Button>(R.id.btn_back).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signin -> {

                val intent = Intent().setClass(this@MainActivity, SecondActivity::class.java)
                val editText1 = findViewById<View>(R.id.text_account) as EditText
                val editText2 = findViewById<View>(R.id.text_password) as EditText

                val account = editText1.text.toString()
                val password = editText2.text.toString()
                intent.putExtra("Name", account)
                intent.putExtra("Password", password)
                startActivity(intent)
                finish()
            }
            R.id.btn_back -> {
                //ActivityCollector.finishAll()
                exitProcess()
            }


        }
    }

    private fun exitProcess() {
        System.exit(-1)
    }
}