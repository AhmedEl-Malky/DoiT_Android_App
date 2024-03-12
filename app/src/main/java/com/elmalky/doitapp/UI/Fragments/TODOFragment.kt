package com.elmalky.doitapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elmalky.doitapp.databinding.FragmentTODOBinding


class TODOFragment : Fragment() {
    lateinit var binder: FragmentTODOBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentTODOBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.todoToolbar.subtitle = "todos_num"
    }

}