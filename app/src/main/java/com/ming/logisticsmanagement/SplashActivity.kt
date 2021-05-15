package com.ming.logisticsmanagement

import android.content.Intent
import android.os.Handler
import com.ming.logisticsmanagement.contract.SplashContract

class SplashActivity : BaseActivity() , SplashContract.View {

    companion object{
        val DELAY = 2000L
    }

    val handler by lazy {
        Handler()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }



    //延时2s，跳转到登录页面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity(Intent().setClass(this@SplashActivity,LoginActivity::class.java))
            finish()
        },DELAY)
    }

    //跳转到主界面
    override fun onLoggedIn() {
        startActivity(Intent().setClass(this@SplashActivity,MainActivity::class.java))
    }
}