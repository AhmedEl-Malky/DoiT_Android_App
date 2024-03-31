package com.elmalky.doitapp.util

import androidx.fragment.app.Fragment
import com.elmalky.doitapp.ui.Activities.MainActivity
import com.elmalky.doitapp.ui.Fragments.NoteFragment
import com.elmalky.doitapp.ui.Fragments.TODOFragment


object Constants {
    object Fragments {
        val noteFragment = NoteFragment()
        val todoFragment = TODOFragment()
        val fragmentsList = listOf<Fragment>(noteFragment, todoFragment)
    }

    object Activities {
        val mainActivity = MainActivity()
    }

    object Names {
        const val SP_NAME = "DOIT_SP"
        const val FIRST_START = "FIRST_START"
        const val NOTE_NAME = "NOTE"
    }
}