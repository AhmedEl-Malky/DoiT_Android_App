package com.elmalky.doitapp.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.FragmentNoteBinding
import com.elmalky.doitapp.ui.Activities.TypingNoteActivity
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
            val intent = Intent(requireActivity(), TypingNoteActivity::class.java)
            startActivity(intent)
        }
    }

}