package com.elmalky.doitapp.diff

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtil<T>(
    val oldItems: List<T>,
    val newItems: List<T>,
    val checkIfItemsTheSame: (oldItems: T, newItems: T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkIfItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkIfItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
}