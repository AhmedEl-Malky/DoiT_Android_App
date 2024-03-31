package com.elmalky.doitapp.models.repository

import com.elmalky.doitapp.models.dataBase.databases.AppDatabase
import com.elmalky.doitapp.models.dataBase.entities.Note

class NoteRepository {
    private val dao = AppDatabase.getInstance().getNoteDao()
    fun getAllNotes() = dao.getAllNotes()
    suspend fun insertNewNote(note: Note) {
        dao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}