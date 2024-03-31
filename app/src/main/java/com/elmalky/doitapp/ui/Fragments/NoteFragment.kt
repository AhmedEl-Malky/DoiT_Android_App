package com.elmalky.doitapp.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elmalky.doitapp.R
import com.elmalky.doitapp.adapters.NoteInteractions
import com.elmalky.doitapp.adapters.NoteRecyclerAdapter
import com.elmalky.doitapp.databinding.FragmentNoteBinding
import com.elmalky.doitapp.models.dataBase.entities.Note
import com.elmalky.doitapp.ui.Activities.TypingNoteActivity
import com.elmalky.doitapp.util.Constants
import com.elmalky.doitapp.viewModels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteFragment : Fragment(), NoteInteractions {
    lateinit var binder: FragmentNoteBinding
    lateinit var noteFab: FloatingActionButton
    val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentNoteBinding.inflate(layoutInflater)
        binder.lifecycleOwner = this
        binder.vm = viewModel
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteFab = requireActivity().findViewById(R.id.note_fab)
        binder.noteRecycler.adapter = NoteRecyclerAdapter(emptyList(), this)
        noteFab.setOnClickListener {
            val intent = Intent(requireActivity(), TypingNoteActivity::class.java)
            startActivity(intent)
        }
        callBacks()
    }

    override fun onNoteClickListener(note: Note) {
        val intent = Intent(requireActivity(), TypingNoteActivity::class.java)
        intent.putExtra(Constants.Names.NOTE_NAME, note)
        startActivity(intent)
    }

    fun callBacks() {
        binder.recyclerGridViewBtn.setOnClickListener {
            binder.noteRecycler.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
        binder.recyclerListViewBtn.setOnClickListener {
            binder.noteRecycler.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}