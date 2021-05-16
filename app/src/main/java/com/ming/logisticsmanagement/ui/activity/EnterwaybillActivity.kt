package com.ming.logisticsmanagement.ui.activity

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.WaybillRoom.WaybillDao
import com.ming.logisticsmanagement.viewmodel.FindUserModel
import com.ming.logisticsmanagement.viewmodel.FindWaybillModel
import kotlinx.android.synthetic.main.activity_enterwaybill.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class EnterwaybillActivity: BaseActivity() {

    lateinit var waybillviewModel: FindWaybillModel
    lateinit var  userviewModel: FindUserModel

    private val userName:String? by lazy { intent.getStringExtra("userName") }
    private val password:String? by lazy { intent.getStringExtra("password") }

    override fun getLayoutResId(): Int = R.layout.activity_enterwaybill

    override fun init() {
        super.init()

        back.setOnClickListener {
            startActivity<MainActivity>("userName" to userName, "password" to password)
        }
        save.setOnClickListener { Enterwaybill() }
    }

    override fun initData() {
        super.initData()
        waybillviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindWaybillModel::class.java)
        userviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindUserModel::class.java)
    }

    fun Enterwaybill(){
        //隐藏软键盘
        hideSoftKeyboard()
        userviewModel.findUser= userName?.let { userdao.getUser(it) }
        val consignor = consignor.text.trim().toString()
        val consignorPhoneNumber = consignorPhoneNumber.text.trim().toString()
        val consignee = consignee.text.trim().toString()
        val consigneePhoneNumber = consigneePhoneNumber.text.trim().toString()
        val transportationArrivalStation = transportationArrivalStation.text.trim().toString()
        val goodsName = goodsName.text.trim().toString()
        val numberOfPackages = numberOfPackages.text.trim().toString()
        val FreightPaidByConsignor = FreightPaidByConsignor.text.trim().toString()
        val FreightPaidByConsignee = FreightPaidByConsignee.text.trim().toString()
        var DepartureStation = "沈阳"
        userviewModel.findUser?.let {
            userviewModel.findUser!!.observe(this,{
                if (it!=null){
                    DepartureStation = userviewModel.findUser!!.value?.getcity().toString()
                }
            })
        }
        if (transportationArrivalStation.isNotEmpty()){
            if (goodsName.isNotEmpty()){
                if (numberOfPackages.isNotEmpty()){
                    var waybill = Waybill(consignor,consignorPhoneNumber,consignee,consigneePhoneNumber,DepartureStation,transportationArrivalStation,goodsName,numberOfPackages,FreightPaidByConsignor,FreightPaidByConsignee)
                    InsertAsyncTask(waybilldao).execute(waybill)
                    //增设查询该条记录
                    toast("插入成功")
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        startActivity<MainActivity>("userName" to userName, "password" to password)
                        finish()
                    },2000)
                }else{
                    toast("件数不能为空")
                }
            }else{
                toast("货物名称不能为空")
            }
        }else{
            toast("到站不能为空")
        }
    }


    internal class InsertAsyncTask(private val waybillDao: WaybillDao):AsyncTask<Waybill?,Unit,Unit>(){
        override fun doInBackground(vararg params: Waybill?) {
            publishProgress()
            waybillDao.insertWaybills(*params)
        }
    }
}