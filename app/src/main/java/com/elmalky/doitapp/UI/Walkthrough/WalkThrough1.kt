package com.elmalky.doitapp.UI.Walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentWalkThrough1Binding


class WalkThrough1 : Fragment() {
    lateinit var binder: FragmentWalkThrough1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentWalkThrough1Binding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.startBtn.setOnClickListener {
            findNavController().navigate(R.id.action_walkThrough1_to_walkThrough2)
        }
    }

}