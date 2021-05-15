package com.ming.logisticsmanagement

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.ming.logisticsmanagement.contract.SplashContract
import com.ming.logisticsmanagement.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() , SplashContract.View {

    val presenter = SplashPresenter(this)

    companion object{
        const val DELAY = 2000L
    }

    val handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    //延时2s，跳转到登录页面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },DELAY)
    }

    //跳转到主界面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }
}