package com.elmalky.doitapp.util

import com.elmalky.doitapp.ui.Fragments.NoteFragment
import com.elmalky.doitapp.ui.Fragments.TODOFragment


object Constants {
    object Fragments {
        val noteFragment = NoteFragment()
        val todoFragment = TODOFragment()
        val fragmentsList = listOf(noteFragment, todoFragment)
    }

    object Names {
        const val SP_NAME = "DOIT_SP"
        const val FIRST_START = "FIRST_START"
    }
}