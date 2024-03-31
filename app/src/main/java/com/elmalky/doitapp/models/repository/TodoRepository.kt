package com.elmalky.doitapp.models.repository

import com.elmalky.doitapp.models.dataBase.databases.AppDatabase
import com.elmalky.doitapp.models.dataBase.entities.TODO

class TodoRepository {
    private val dao = AppDatabase.getInstance().getTodoDao()
    fun getAllTodos() = dao.getAllTodos()
    suspend fun insertTodo(todo: TODO) {
        dao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: TODO) {
        dao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: TODO) {
        dao.deleteTodo(todo)
    }

}