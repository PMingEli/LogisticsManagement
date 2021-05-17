package com.ming.logisticsmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ming.logisticsmanagement.R
import com.ming.logisticsmanagement.WaybillRoom.Waybill
import com.ming.logisticsmanagement.widget.WaybillListItemView
import kotlinx.android.synthetic.main.view_waybill_item.view.*

class WaybillListAdapter(val context: Context,val waybillListItems: MutableList<Waybill>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class waybillListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): waybillListItemViewHolder {
        return waybillListItemViewHolder(WaybillListItemView(context))
    }

    override fun getItemCount(): Int = waybillListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val waybillListItemView =  holder.itemView as WaybillListItemView
        waybillListItemView.bindView(waybillListItems[position])
    }


}