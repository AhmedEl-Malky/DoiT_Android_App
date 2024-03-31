package com.elmalky.doitapp.ui.Activities

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.ActivityTypingNoteBinding
import com.elmalky.doitapp.databinding.DialogNoteColorBinding
import com.elmalky.doitapp.models.dataBase.entities.Note
import com.elmalky.doitapp.util.Constants
import com.elmalky.doitapp.viewModels.TypingNoteViewModel

class TypingNoteActivity : AppCompatActivity() {
    lateinit var binder: ActivityTypingNoteBinding
    lateinit var noteColorDialog: DialogNoteColorBinding
    lateinit var colorDialogBuilder: AlertDialog.Builder
    lateinit var colorPickerDialog: AlertDialog
    val nViewModel: TypingNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_typing_note)
        binder.lifecycleOwner = this
        binder.nvm = nViewModel
        nViewModel.noteTitle.observe(this) {
            Log.i("MYTAG", it)
        }
        setUpActivity()
        setUpColorPicker()
        binder.noteColorFab.setOnClickListener {
            openColorPicker()
        }
        val note = intent.getParcelableExtra<Note>(Constants.Names.NOTE_NAME)
        if (note != null)
            setNoteItemToLiveData(note)
    }

    private fun openColorPicker() {
        colorPickerDialog.show()
    }

    fun setUpColorPicker() {
        noteColorDialog = DialogNoteColorBinding.inflate(layoutInflater)
        val view = LayoutInflater.from(this)
            .inflate(R.layout.dialog_note_color, noteColorDialog.noteColorPicker)
        colorDialogBuilder = AlertDialog.Builder(this)
        colorDialogBuilder.setView(view)
        colorPickerDialog = colorDialogBuilder.create()
        colorPickerDialog.window?.setBackgroundDrawable(ColorDrawable(0))

    }

    private fun setUpActivity() {
        binder.typingNoteBody.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        typingNoteNavigation()
    }

    fun changeNoteColor(view: View) {
        nViewModel.changeNoteColor(view.tag.toString())
        colorPickerDialog.dismiss()
    }

    private fun typingNoteNavigation() {
        binder.typingNoteBack.setOnClickListener {
            finish()
        }
    }

    fun addNewNote(view: View) {
        nViewModel.addNewNote()
        finish()
    }

    fun setNoteItemToLiveData(note: Note) {
        nViewModel.noteTitle.postValue(note.title)
        nViewModel.noteContent.postValue(note.content)
        nViewModel.noteColor.postValue(note.color)
        binder.typingNoteBack.setOnClickListener {
            nViewModel.updateNote(note)
            finish()
        }
        binder.submitTypingNote.setOnClickListener {
            nViewModel.updateNote(note)
            finish()
        }
    }
}