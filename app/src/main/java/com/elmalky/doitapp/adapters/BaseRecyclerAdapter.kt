package com.elmalky.doitapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elmalky.doitapp.BR
import com.elmalky.doitapp.diff.SimpleDiffUtil


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
                holder.binder.setVariable(BR.item, currentItem)
                holder.binder.setVariable(BR.listener, listener)
            }
        }
    }

    fun updateItems(newItems: List<T>) {
        val diff = DiffUtil.calculateDiff(SimpleDiffUtil(items, newItems) { oldItem, newItem ->
            areItemsSame(oldItem, newItem)
        })
        items = newItems
        diff.dispatchUpdatesTo(this)
    }

    fun areItemsSame(oldItems: T, newItems: T): Boolean {
        return oldItems?.equals(newItems) == true
    }

    class ItemViewHolder(val binder: ViewDataBinding) : BaseViewHolder(binder)

    abstract class BaseViewHolder(private val binder: ViewDataBinding) :
        RecyclerView.ViewHolder(binder.root)

}