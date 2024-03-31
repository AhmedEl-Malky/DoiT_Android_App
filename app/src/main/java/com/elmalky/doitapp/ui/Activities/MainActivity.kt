package com.elmalky.doitapp.ui.Activities

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.elmalky.doitapp.R
import com.elmalky.doitapp.adapters.ViewPager
import com.elmalky.doitapp.databinding.ActivityMainBinding
import com.elmalky.doitapp.util.Constants
import com.elmalky.doitapp.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    lateinit var popUp: Animation
    lateinit var popDown: Animation
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpViewPager()
        binder.vm = viewModel
        binder.lifecycleOwner = this
        setUpAnimations()
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
                        binder.todoFab.hide()
                        binder.noteFab.show()
                        binder.todoFab.startAnimation(popDown)
                        binder.todoFab.startAnimation(popUp)
                        binder.bottomNavBar.selectedItemId = R.id.noteFragment
                    }

                    1 -> {
                        binder.noteFab.hide()
                        binder.todoFab.show()
                        binder.noteFab.startAnimation(popDown)
                        binder.noteFab.startAnimation(popUp)
                        binder.bottomNavBar.selectedItemId = R.id.TODOFragment
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

    fun changeTodoPriority(view: View) {
        viewModel.todoPriority.postValue(view.tag.toString())
        when (view.tag.toString()) {
            "High Priority" -> viewModel.todoPriorityColor.postValue("#f03e3e")
            "Mid Priority" -> viewModel.todoPriorityColor.postValue("#FCC419")
            "Low Priority" -> viewModel.todoPriorityColor.postValue("#339AF0")
        }
        Constants.Fragments.todoFragment.priorityAlertDialog.dismiss()
    }

}