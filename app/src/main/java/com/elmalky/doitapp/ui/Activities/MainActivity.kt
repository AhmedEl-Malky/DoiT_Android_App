package com.elmalky.doitapp.ui.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.elmalky.doitapp.R
import com.elmalky.doitapp.adapters.ViewPager
import com.elmalky.doitapp.databinding.ActivityMainBinding
import com.elmalky.doitapp.util.Constants

class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        setUpViewPager()
        bottomNavBarNavigations()
        viewPagerNavigations()
    }

    private fun setUpViewPager() {
        binder.mainViewPager.adapter = ViewPager(this, Constants.Fragments.fragmentsList)
    }

    private fun bottomNavBarNavigations() {
        binder.bottomNavBar.apply {
            selectedItemId = R.id.todos_frag
            menu.findItem(R.id.todos_frag).setIcon(R.drawable.todo_icon_filled)
            setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.notes_frag -> {
                        binder.mainViewPager.currentItem = 0
                        menuItem.setIcon(R.drawable.notes_icon_filled)
                        menu.findItem(R.id.todos_frag).setIcon(R.drawable.todo_icon)
                        return@setOnItemSelectedListener true
                    }

                    R.id.todos_frag -> {
                        binder.mainViewPager.currentItem = 1
                        menuItem.setIcon(R.drawable.todo_icon_filled)
                        menu.findItem(R.id.notes_frag).setIcon(R.drawable.notes_icon)
                        return@setOnItemSelectedListener true
                    }

                    else -> {
                        return@setOnItemSelectedListener true
                    }
                }
            }
        }
    }

    private fun viewPagerNavigations() {
        binder.mainViewPager.apply {
            currentItem = 1
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> binder.bottomNavBar.selectedItemId = R.id.notes_frag
                        1 -> binder.bottomNavBar.selectedItemId = R.id.todos_frag
                    }
                    super.onPageSelected(position)
                }
            })
        }
    }
}