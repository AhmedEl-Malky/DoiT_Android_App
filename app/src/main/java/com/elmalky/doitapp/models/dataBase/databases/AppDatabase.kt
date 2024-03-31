package com.elmalky.doitapp.models.dataBase.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elmalky.doitapp.models.dataBase.dao.NotesDao
import com.elmalky.doitapp.models.dataBase.dao.TodoDao
import com.elmalky.doitapp.models.dataBase.entities.Note
import com.elmalky.doitapp.models.dataBase.entities.TODO

@Database(entities = [Note::class, TODO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NotesDao
    abstract fun getTodoDao(): TodoDao

    companion object {
        private const val APP_DATABASE_NAME = "APP_DATABASE"

        @Volatile
        private var instance: AppDatabase? = null
        fun getInstanceByContext(context: Context): AppDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        fun getInstance(): AppDatabase {
            return instance!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()
        }
    }
}