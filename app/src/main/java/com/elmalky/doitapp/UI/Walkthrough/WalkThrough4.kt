package com.elmalky.doitapp.UI.Walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentWalkThrough4Binding

class WalkThrough4 : Fragment() {
    lateinit var binder: FragmentWalkThrough4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentWalkThrough4Binding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_walkThrough4_to_mainActivity2)
            requireActivity().finish()
        }
        binder.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_walkThrough4_to_mainActivity)
            requireActivity().finish()
        }
        binder.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}