package com.elmalky.doitapp.ui.Activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.doitapp.R
import com.elmalky.doitapp.databinding.ActivityTypingNoteBinding

class TypingNoteActivity : AppCompatActivity() {
    lateinit var binder: ActivityTypingNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_typing_note)
        setUpActivity()
        binder.noteColorFab.setOnClickListener {

        }
    }

    private fun setUpActivity() {
        binder.typingNoteBody.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        typingNoteNavigation()
    }

    private fun typingNoteNavigation() {
        binder.typingNoteBack.setOnClickListener {
            finish()
        }
    }
}