package com.ming.logisticsmanagement.ui.activity

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.WaybillRoom.WaybillDao
import com.ming.logisticsmanagement.extensions.isValidPhoneNumber
import com.ming.logisticsmanagement.viewmodel.FindUserModel
import com.ming.logisticsmanagement.viewmodel.FindWaybillModel
import kotlinx.android.synthetic.main.activity_enterwaybill.*
import kotlinx.android.synthetic.main.activity_enterwaybill.consignee
import kotlinx.android.synthetic.main.activity_enterwaybill.consigneePhoneNumber
import kotlinx.android.synthetic.main.activity_enterwaybill.goodsName
import kotlinx.android.synthetic.main.activity_enterwaybill.numberOfPackages
import kotlinx.android.synthetic.main.activity_enterwaybill.transportationArrivalStation
import kotlinx.android.synthetic.main.activity_enterwaybill.transportationDepartureStation
import kotlinx.android.synthetic.main.view_waybill_item.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.Exception

class EnterwaybillActivity: BaseActivity() {

    lateinit var waybillviewModel: FindWaybillModel
    lateinit var  userviewModel: FindUserModel

    private val userName:String? by lazy { intent.getStringExtra("userName") }
    private val password:String? by lazy { intent.getStringExtra("password") }

    override fun getLayoutResId(): Int = R.layout.activity_enterwaybill

    override fun init() {
        super.init()

        userviewModel.findUser= userName?.let { userdao.getUser(it) }
        userviewModel.findUser?.let {
            userviewModel.findUser!!.observe(this,{
                if (it!=null){
                    transportationDepartureStation.text = userviewModel.findUser!!.value?.getcity().toString()
                }
            })
        }

        back.setOnClickListener {
            startActivity<MainActivity>("userName" to userName, "password" to password)
            finish()
        }
        save.setOnClickListener {
            Enterwaybill()
        }
    }

    override fun initData() {
        super.initData()
        waybillviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindWaybillModel::class.java)
        userviewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FindUserModel::class.java)
    }

    fun Enterwaybill(){
        //???????????????
        hideSoftKeyboard()

        val consignor = consignor.text.trim().toString()
        val consignorPhoneNumber = consignorPhoneNumber.text.trim().toString()
        val consignee = consignee.text.trim().toString()
        val consigneePhoneNumber = consigneePhoneNumber.text.trim().toString()
        val transportationArrivalStation = transportationArrivalStation.text.trim().toString()
        val goodsName = goodsName.text.trim().toString()
        val numberOfPackages = numberOfPackages.text.trim().toString()
        val FreightPaidByConsignor = FreightPaidByConsignor.text.trim().toString()
        val FreightPaidByConsignee = FreightPaidByConsignee.text.trim().toString()
        val transportationDepartureStation = transportationDepartureStation.text.trim().toString()
        try {
            if (transportationArrivalStation.isNotEmpty()){
                if (goodsName.isNotEmpty()){
                    if (numberOfPackages.isNotEmpty()){
                        if(consignorPhoneNumber.isValidPhoneNumber()){
                            if (consigneePhoneNumber.isValidPhoneNumber()){
                                val waybill = Waybill(consignor,consignorPhoneNumber,consignee,consigneePhoneNumber,transportationDepartureStation,transportationArrivalStation,goodsName,numberOfPackages,FreightPaidByConsignor,FreightPaidByConsignee)
                                InsertAsyncTask(waybilldao).execute(waybill)
                                //????????????????????????
                                toast("????????????")
                                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                                    startActivity<MainActivity>("userName" to userName, "password" to password)
                                    finish()
                                },2000)
                            }else{
                                toast("??????????????????????????????????????????11????????????????????????")
                            }
                        }else{
                            toast("??????????????????????????????????????????11????????????????????????")
                        }

                    }else{
                        toast("??????????????????")
                    }
                }else{
                    toast("????????????????????????")
                }
            }else{
                toast("??????????????????")
            }
        }catch (e:Exception){
            startActivity<MainActivity>("userName" to userName, "password" to password)
            finish()
        }

    }


    internal class InsertAsyncTask(private val waybillDao: WaybillDao):AsyncTask<Waybill?,Unit,Unit>(){
        override fun doInBackground(vararg params: Waybill?) {
            publishProgress()
            waybillDao.insertWaybills(*params)
        }
    }
}