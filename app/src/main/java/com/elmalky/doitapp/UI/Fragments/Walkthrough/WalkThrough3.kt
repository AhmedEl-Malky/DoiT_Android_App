package com.elmalky.doitapp.UI.Fragments.Walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentWalkThrough3Binding

class WalkThrough3 : Fragment() {
    lateinit var binder: FragmentWalkThrough3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentWalkThrough3Binding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_walkThrough3_to_walkThrough4)
        }
        binder.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_walkThrough3_to_mainActivity)
            requireActivity().finish()
        }
        binder.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}