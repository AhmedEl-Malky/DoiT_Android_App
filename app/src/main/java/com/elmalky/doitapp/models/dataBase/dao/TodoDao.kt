package com.elmalky.doitapp.models.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.elmalky.doitapp.models.dataBase.entities.TODO
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: TODO)

    @Delete
    suspend fun deleteTodo(todo: TODO)

    @Update
    suspend fun updateTodo(todo: TODO)

    @Query("select * from TODO order by id desc")
    fun getAllTodos(): Flow<List<TODO>>
}