package com.elmalky.doitapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmalky.doitapp.models.dataBase.entities.Note
import com.elmalky.doitapp.models.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class TypingNoteViewModel : ViewModel() {
    val noteRepo = NoteRepository()
    val noteContent = MutableLiveData<String>()
    val noteTitle = MutableLiveData<String>()
    val noteColor = MutableLiveData("#8692f7")
    val noteDate =
        MutableLiveData(Date().toString().substring(0, 10) + Date().toString().substring(29, 34))

    fun changeNoteColor(color: String) {
        noteColor.postValue(color)
    }

    fun addNewNote() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("MyTAG", "ADDED")
            val newNote =
                Note(title = noteTitle.value, content = noteContent.value, color = noteColor.value)
            noteRepo.insertNewNote(newNote)
        }
        resetLiveData()
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val newNote = note.copy(
                title = noteTitle.value,
                content = noteContent.value,
                color = noteColor.value
            )
            noteRepo.updateNote(newNote)
        }
    }

    private fun resetLiveData() {
        noteContent.postValue("")
        noteTitle.postValue("")
        noteColor.postValue("#8692f7")
        noteDate.postValue("")
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.deleteNote(note)
        }
    }
}