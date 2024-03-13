package com.elmalky.doitapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager(container: FragmentActivity, val fragments: List<Fragment>) :
    FragmentStateAdapter(container) {
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}