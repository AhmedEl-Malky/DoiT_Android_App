package com.elmalky.doitapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.elmalky.doitapp.models.dataBase.entities.Note
import com.elmalky.doitapp.models.dataBase.entities.TODO
import com.elmalky.doitapp.models.repository.NoteRepository
import com.elmalky.doitapp.models.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val noteRepo = NoteRepository()
    val todoRepo = TodoRepository()
    val todoTask = MutableLiveData("")
    val todoPriority = MutableLiveData("")
    val todoPriorityColor = MutableLiveData("#8692f7")

    val allTodos: LiveData<List<TODO>> = todoRepo.getAllTodos().asLiveData()

    val allNotes: LiveData<List<Note>> = noteRepo.getAllNotes().asLiveData()
    fun addNewTODO(todoTask: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTODO = TODO(
                task = todoTask,
                priority = todoPriority.value,
                priorityColor = todoPriorityColor.value
            )
            todoRepo.insertTodo(newTODO)
        }
        resetLiveData()
    }

    private fun resetLiveData() {
        todoPriority.postValue("")
        todoPriorityColor.postValue("#8692f7")
    }

    fun deleteTODO(todo: TODO) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.deleteTodo(todo)
        }
    }

    fun updateTODO(todo: TODO, newTask: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTodo = todo.copy(
                task = newTask,
                priority = todo.priority,
                priorityColor = todo.priorityColor
            )
            todoRepo.updateTodo(newTodo)
        }
        resetLiveData()
    }
}