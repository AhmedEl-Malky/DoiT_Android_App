package com.elmalky.doitapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentNoteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteFragment : Fragment() {
    lateinit var binder: FragmentNoteBinding
    lateinit var noteFab: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentNoteBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteFab = requireActivity().findViewById(R.id.note_fab)
        noteFab.setOnClickListener {
            Toast.makeText(requireActivity(), "Hello", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        val animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.pop_up)
//        binder.noteFab.startAnimation(animation)
//
//
//    }

//    override fun onPause() {
//        super.onPause()
//        val animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.pop_down)
//        binder.noteFab.startAnimation(animation)
//    }
}