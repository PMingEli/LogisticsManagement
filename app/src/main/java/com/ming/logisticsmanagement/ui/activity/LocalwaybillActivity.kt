package com.ming.logisticsmanagement.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.adapter.WaybillListAdapter
import com.ming.logisticsmanagement.contract.WaybillContract
import kotlinx.android.synthetic.main.fragment_localbill.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class LocalwaybillActivity: BaseActivity(),WaybillContract.View {

    val waybillListItems = mutableListOf<Waybill>()

    private val userName:String? by lazy { intent.getStringExtra("userName") }
    private val password:String? by lazy { intent.getStringExtra("password") }

    override fun getLayoutResId(): Int = R.layout.fragment_localbill

    override fun init() {
        super.init()
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
            adapter = WaybillListAdapter(context,waybillListItems)
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