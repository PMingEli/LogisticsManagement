package com.ming.logisticsmanagement.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.widget.WaybillListItemView

class WaybillListAdapter(val context: Context,val waybillListItems: MutableList<Waybill>,val page:String) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class waybillListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): waybillListItemViewHolder {
        return waybillListItemViewHolder(WaybillListItemView(context))
    }

    override fun getItemCount(): Int = waybillListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val waybillListItemView =  holder.itemView as WaybillListItemView
        waybillListItemView.bindView(waybillListItems[position],page)
    }


}