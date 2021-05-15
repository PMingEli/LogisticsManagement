package com.ming.logisticsmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    open fun init(){
        //初始化一些公共的功能，子类也可以复写该方法，完成自己的初始化
    }


    //子类必须实现方法返回一个布局资源的id
    abstract fun getLayoutResId(): Int
}