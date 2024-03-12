package com.elmalky.doitapp.adapters

import com.elmalky.doitapp.R
import com.elmalky.doitapp.models.dataBase.entities.Note

class NoteRecyclerAdapter(val items: List<Note>, val listener: NoteInteractions) :
    BaseRecyclerAdapter<Note>(items, listener) {
    override val layoutId = R.layout.item_note
}

interface NoteInteractions : BaseItemInteractions