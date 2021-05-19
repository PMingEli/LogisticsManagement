package com.ming.logisticsmanagement.presenter

import android.util.Log
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.contract.WaybillContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.Exception
import java.net.URL

class WaybillPresenter(val page:String,val view:WaybillContract.View):WaybillContract.Presenter {

    val waybills = mutableListOf<Waybill>()
    var waybillNo = ""
    var consignor = ""
    var consignorPhoneNumber = ""
    var consignee = ""
    var consigneePhoneNumber = ""
    var transportationDepartureStation=""
    var transportationArrivalStation=""
    var goodsName = ""
    var numberOfPackages = ""
    var freightPaidByTheReceivingParty=""
    var freightPaidByConsignor = ""
    lateinit var waybill:Waybill

    override fun loadWaybills() {
        waybills.clear()
        if (page == "xml"){
            doAsync {
                try {
                    val xmlString = URL("http://60.12.122.142:6080/simulated-Waybills-db.xml").readText()
                    //println(xmlString)
                    // 初始化
                    // 获取xmlpull解析器工厂
                    val factory = XmlPullParserFactory.newInstance()
                    // 利用工厂生成一个解析器
                    val parser = factory.newPullParser()
                    // 放入解析源
                    parser.setInput(StringReader(xmlString))
                    var currentType = parser.eventType



                    while(currentType != XmlPullParser.END_DOCUMENT){
                        // 思路：
                        // 1、扫描到标签开始时，如果这个标签name是什么
                        //		则赋值给上面定义的变量
                        // 2、扫描到结束标签时，这个标签的name时waybillRecord时，则打印上述变量
                        // 3、在单次while循环结束之前，currentType 要赋值下一个事件
                        when(currentType){
                            XmlPullParser.START_TAG ->
                                when(parser.name){
                                    "waybillNo" -> waybillNo = parser.nextText()
                                    "consignor" -> consignor = parser.nextText()
                                    "consignorPhoneNumber" -> consignorPhoneNumber = parser.nextText()
                                    "consignee"->consignee=parser.nextText()
                                    "consigneePhoneNumber"->consigneePhoneNumber=parser.nextText()
                                    "transportationDepartureStation"->transportationDepartureStation=parser.nextText()
                                    "transportationArrivalStation"->transportationArrivalStation=parser.nextText()
                                    "goodsName"->goodsName=parser.nextText()
                                    "numberOfPackages"->numberOfPackages=parser.nextText()
                                    "freightPaidByTheReceivingParty"->freightPaidByTheReceivingParty=parser.nextText()
                                    "freightPaidByConsignor"->freightPaidByConsignor=parser.nextText()
                                }
                            XmlPullParser.END_TAG ->
                                if(parser.name == "waybillRecord"){
                                    Log.e("tag",waybillNo )
                                    Log.e("tag",consignor )
                                    Log.e("tag",consignorPhoneNumber )
                                    Log.e("tag",consignee )
                                    Log.e("tag",consigneePhoneNumber )
                                    Log.e("tag",transportationDepartureStation )
                                    Log.e("tag",transportationArrivalStation )
                                    Log.e("tag",goodsName )
                                    Log.e("tag",numberOfPackages )
                                    Log.e("tag",freightPaidByTheReceivingParty )
                                    Log.e("tag",freightPaidByConsignor )
                                    Log.e("tag",waybillNo.substring(1))
                                    waybill = Waybill(consignor,consignorPhoneNumber,consignee,consigneePhoneNumber,transportationDepartureStation,transportationArrivalStation,goodsName,numberOfPackages,freightPaidByConsignor,freightPaidByTheReceivingParty)
                                    waybill.waybillNo = waybillNo.substring(1).toLong()
                                    Log.e("waybillNo",waybill.waybillNo.toString())
                                    waybills.add(waybill)
                                    Log.e("waybillsize",waybills.size.toString())
                                }
                        }
                        currentType = parser.next()
                    }
                    uiThread { view.onLoadWaybillsSuccess() }
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.e("tag", "解析出错：${e} ")
                    uiThread { view.onLoadWaybillsFailed() }
                }
            }
        }else if(page=="json"){
            doAsync {
                try {
                    val jsonString = URL("http://60.12.122.142:6080/simulated-Waybills-db.json").readText()
                    //Log.e("tag",jsonString)
                    val jsonData = JSONObject(jsonString)
                    val json = jsonData.getString("waybillRecord")
                    val jsonArray = JSONArray(json)
                    Log.e("tag",jsonArray.toString())
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        waybillNo = jsonObject.getString("waybillNo")
                        consignor = jsonObject.getString("consignor")
                        consignorPhoneNumber = jsonObject.getString("consignorPhoneNumber")
                        consignee = jsonObject.getString("consignee")
                        consigneePhoneNumber = jsonObject.getString("consigneePhoneNumber")
                        goodsName = jsonObject.getString("goodsName")
                        numberOfPackages = jsonObject.getString("numberOfPackages")
                        transportationDepartureStation = jsonObject.getString("transportationDepartureStation")
                        transportationArrivalStation = jsonObject.getString("transportationArrivalStation")
                        freightPaidByConsignor = jsonObject.getString("freightPaidByConsignor")
                        freightPaidByTheReceivingParty = jsonObject.getString("freightPaidByTheReceivingParty")
                        Log.e("tag",waybillNo)
                        waybill = Waybill(consignor,consignorPhoneNumber,consignee,consigneePhoneNumber,transportationDepartureStation,transportationArrivalStation,goodsName,numberOfPackages,freightPaidByConsignor,freightPaidByTheReceivingParty)
                        waybill.waybillNo = waybillNo.substring(1).toLong()
                        waybills.add(waybill)
                    }
                    uiThread { view.onLoadWaybillsSuccess() }
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.e("tag", "解析出错：${e} ")
                    uiThread { view.onLoadWaybillsFailed() }
                }
            }
        }
    }
}