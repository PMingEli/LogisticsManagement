package com.ming.logisticsmanagement.ui.activity

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.adapter.WaybillListAdapter
import com.ming.logisticsmanagement.contract.WaybillContract
import com.ming.logisticsmanagement.presenter.WaybillPresenter
import kotlinx.android.synthetic.main.fragment_localbill.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class WaybillActivity: BaseActivity(),WaybillContract.View {

    lateinit var presenter: WaybillPresenter

    val waybillListItems = mutableListOf<Waybill>()

    private val page:String? by lazy { intent.getStringExtra("page") }
    private val userName:String? by lazy { intent.getStringExtra("userName") }
    private val password:String? by lazy { intent.getStringExtra("password") }

    override fun getLayoutResId(): Int = R.layout.fragment_localbill

    override fun init() {
        super.init()
        back.setOnClickListener {
            startActivity<MainActivity>("userName" to userName, "password" to password)
            finish()
        }

        when(page){
            "local" ->{
                headerTitle.text = getString(R.string.localwaybill)
                SwipeRefreshLayout.apply {
                    setColorSchemeResources(R.color.bg_blue)
                    isRefreshing = true
                    setOnRefreshListener { onLoadWaybills() }
                }

                onLoadWaybills()
                RecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = WaybillListAdapter(context,waybillListItems,"local")
                }
            }
            "xml" ->{
                headerTitle.text = getString(R.string.xmlwaybill)
                presenter = WaybillPresenter(page!!,this)
                presenter.loadWaybills()
                Log.e("presenter.waybills",presenter.waybills.size.toString())
                SwipeRefreshLayout.apply {
                    setColorSchemeResources(R.color.bg_blue)
                    isRefreshing = true
                    setOnRefreshListener { presenter.loadWaybills() }
                }
                RecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = WaybillListAdapter(context,presenter.waybills,"xml")
                }

            }
            "json"->{
                headerTitle.text = getString(R.string.jsonwaybill)
                presenter = WaybillPresenter(page!!,this)
                presenter.loadWaybills()
                Log.e("presenter.waybills",presenter.waybills.size.toString())
                SwipeRefreshLayout.apply {
                    setColorSchemeResources(R.color.bg_blue)
                    isRefreshing = true
                    setOnRefreshListener { presenter.loadWaybills() }
                }
                RecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = WaybillListAdapter(context,presenter.waybills,"json")
                }

            }
            else ->{
                startActivity<MainActivity>("userName" to userName, "password" to password)
                finish()
            }
        }
    }

    fun onLoadWaybills(){
        waybillListItems.clear()
        doAsync {
            val waybills = waybilldao.getAllWaybills()
            if (waybills.isNotEmpty()) {
                waybills.forEach{
                    waybillListItems.add(it)
                }
                onLoadWaybillsSuccess()
                toast("加载成功")
            }else{
                onLoadWaybillsFailed()
            }
        }
    }

    override fun onLoadWaybillsSuccess() {
        SwipeRefreshLayout.isRefreshing = false
        RecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadWaybillsFailed() {
        SwipeRefreshLayout.isRefreshing = false
        toast(R.string.load_waybills_failed)
    }
}