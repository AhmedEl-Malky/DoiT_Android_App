package com.elmalky.doitapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


interface BaseItemInteractions
abstract class BaseRecyclerAdapter<T>(
    private var items: List<T>,
    private val listener: BaseItemInteractions
) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    abstract val layoutId: Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
//                holder.binder.setVariable(BR.item,currentItem)
//                holder.binder.setVariable(BR.listener, listener)
            }
        }
    }

    class ItemViewHolder(val binder: ViewDataBinding) : BaseViewHolder(binder)

    abstract class BaseViewHolder(private val binder: ViewDataBinding) :
        RecyclerView.ViewHolder(binder.root)

}