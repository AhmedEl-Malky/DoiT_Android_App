package com.elmalky.doitapp.ui.Activities

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.elmalky.doitapp.R
import com.elmalky.doitapp.adapters.ViewPager
import com.elmalky.doitapp.databinding.ActivityMainBinding
import com.elmalky.doitapp.util.Constants

class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    lateinit var popUp: Animation
    lateinit var popDown: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpAnimations()
        setUpViewPager()
    }

    private fun setUpAnimations() {
        popUp = AnimationUtils.loadAnimation(this, R.anim.pop_up)
        popDown = AnimationUtils.loadAnimation(this, R.anim.pop_down)
    }

    private fun setUpViewPager() {
        val viewPagerAdapter = ViewPager(this, Constants.Fragments.fragmentsList)
        binder.mainViewPager.adapter = viewPagerAdapter
        binder.mainViewPager.currentItem = 1
        viewPagerNavigations()
        bottomNavBarNavigations()
    }

    private fun viewPagerNavigations() {
        binder.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binder.noteFab.show()
                        binder.todoFab.hide()
                        binder.bottomNavBar.selectedItemId = R.id.noteFragment
                        binder.todoFab.startAnimation(popDown)
                        binder.todoFab.startAnimation(popUp)
                    }

                    1 -> {
                        binder.todoFab.show()
                        binder.noteFab.hide()
                        binder.bottomNavBar.selectedItemId = R.id.TODOFragment
                        binder.noteFab.startAnimation(popDown)
                        binder.noteFab.startAnimation(popUp)
                    }
                }
            }
        })
    }


    private fun bottomNavBarNavigations() {
        binder.bottomNavBar.apply {
            menu.findItem(R.id.TODOFragment).setIcon(R.drawable.todo_icon_filled)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.noteFragment -> {
                        it.setIcon(R.drawable.notes_icon_filled)
                        binder.mainViewPager.currentItem = 0
                        menu.findItem(R.id.TODOFragment).setIcon(R.drawable.todo_icon)
                        true
                    }

                    R.id.TODOFragment -> {
                        it.setIcon(R.drawable.todo_icon_filled)
                        binder.mainViewPager.currentItem = 1
                        menu.findItem(R.id.noteFragment).setIcon(R.drawable.notes_icon)
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
    }
}