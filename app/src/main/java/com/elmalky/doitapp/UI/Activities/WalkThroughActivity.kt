package com.elmalky.doitapp.UI.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.ActivityWalkThroughBinding

class WalkThroughActivity : AppCompatActivity() {
    lateinit var binder: ActivityWalkThroughBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_walk_through)
    }

}